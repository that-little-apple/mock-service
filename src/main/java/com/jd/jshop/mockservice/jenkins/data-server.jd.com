upstream tomcat_data-server.jd.com {
    server 127.0.0.1:8001  weight=10 max_fails=2 fail_timeout=30s;
}

server
{
    listen 80;

    server_name             *.jd.id *.jd.co.th *.jd.com *.jd.local;
    access_log               /export/Logs/servers/nginx/logs/data-server.jd.com/data-server.jd.com_access.log main;
    error_log                /export/Logs/servers/nginx/logs/data-server.jd.com/data-server.jd.com_error.log info;
    error_page 411 = @my_error;
    root /export/App/data-server.jd.com/;

    location /test {
        proxy_next_upstream     http_500 http_502 http_503 http_504 error timeout invalid_header;
        proxy_set_header        Host  $host;
        proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass              http://tomcat_data-server.jd.com;
        expires                 0;

        # for jdos2.0 nginx monitor
       log_by_lua '
            jdn = require("stat");
            jdn.log()
        ';
    }

    location /{
        proxy_next_upstream     http_500 http_502 http_503 http_504 error timeout invalid_header;
        proxy_set_header        Host  $host;
        proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
       add_header 'Access-Control-Allow-Origin' 'http://m.jd.id';
       add_header 'Access-Control-Allow-Credentials' 'true';
       add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS, PUT';
       add_header 'Access-Control-Allow-Headers' 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type';
       if ($request_method = 'OPTIONS') {
            return 204;
        }
        default_type text/plain;
        set $pass_url '';
        access_by_lua '
            local uri_no_param = nil
            local mockFlag = "false"
            local args = nil
            local method = ngx.var.request_method
            local uri = ngx.var.request_uri
            local host = ngx.var.host
            local callback = nil
            local systemKey = nil
            local backIp = nil
            local backPort = nil

            if  host == "mock-server.jd.com" then
                    ngx.var.pass_url = "http://tomcat_data-server.jd.com"
                    return
            end

            -- 获取对应host配置
            local resp = ngx.location.capture("/backend/hostConfig/getByHost", {
                 method = ngx.HTTP_GET,
                 args = {host = ngx.var.host}
            })
            if resp ~= nil then
                ngx.log(ngx.INFO, "content is ", resp.body)
                local json = require "cjson"
                local data = json.decode(resp.body)

                if data ~= nil then
                    systemKey = data.systemKey
                    backIp = data.backIp
                    backPort = data.backPort
                end
            else
               ngx.log(ngx.INFO, "content is null")
            end
            ngx.log(ngx.INFO, "system is: ", systemKey, ",backIp is: ", backIp, ",backPort is :", backPort)

            -- 获取请求uri
            if uri == nil or uri == "" or systemKey == nil or backIp == nil or backPort == nil then
                ngx.var.pass_url = "http://"..host
            else
                local index = string.find(uri, "?")
                if index ~= nil then
                    uri_no_param = string.sub(uri, 1, index-1)
                else
                    uri_no_param = uri
                end
                if "GET" == method then
                    ngx.log(ngx.INFO, "request method is GET")
                    args = ngx.req.get_uri_args()
                elseif "POST" == method then
                    ngx.log(ngx.INFO, "request method is POST")
                    ngx.req.read_body()
                    args = ngx.req.get_post_args()
                end

                -- 判定mock标识 已经废除
                if args ~= nil then
                    --[[local mockTag = args["mockTag"]
                    if mockTag ~= nil and mockTag == "true" then
                        mockFlag = "true"
                        ngx.log(ngx.INFO, "request need mock data")
                    end--]]
                    callback = args["callback"]
                end

                -- 获取mock状态
                local mockStateResp = ngx.location.capture("/backend/mockData/getMockState", {
                    method = ngx.HTTP_GET,
                    args = {host = ngx.var.host,
                            system = systemKey,
                            uri = uri_no_param
                    }
                })
                if mockStateResp ~= nil and mockStateResp.body ~= nil then
                    ngx.log(ngx.INFO, "getMockState is ", mockStateResp.body)
                    if mockStateResp.body == "true" then
                        mockFlag = "true"
                    end
                end

                -- 获取转发地址
                if  systemKey == "mockServer" then
                    ngx.var.pass_url = "http://tomcat_data-server.jd.com"
                elseif mockFlag == "true" then
                     if callback ~= nil then
                            ngx.var.pass_url = "http://tomcat_data-server.jd.com/mockData/getResponse?uri="..uri_no_param.."&system="..systemKey.."&callback="..callback
                     else
                            ngx.var.pass_url = "http://tomcat_data-server.jd.com/mockData/getResponse?uri="..uri_no_param.."&system="..systemKey
                     end
                else
                    ngx.var.pass_url = "http://"..backIp..":"..backPort
                end
            end
            ngx.log(ngx.INFO, "pass_url is ", ngx.var.pass_url)
        ';
        proxy_pass $pass_url;
    }

   location ~ /backend/(.*) {
        internal;
        proxy_pass http://tomcat_data-server.jd.com/$1$is_args$args;
    }

    # for jdos2.0 nginx monitor
    location = /stat/service {
        access_by_lua_file lua/token.lua;
        content_by_lua '
            cjson = require("cjson")
            local res = {}
            res["data"] = "nginx-1.9.7"
            res["success"] = true
            ngx.say(cjson.encode(res))
        ';
    }

    # for jdos2.0 nginx monitor
    location = /stat/status {
        access_by_lua_file lua/token.lua;
        content_by_lua '
            cjson = require("cjson")
            jdn = require("stat");
            local res = {}
            res["data"] = jdn.stat()
            res["success"] = true
            ngx.say(cjson.encode(res))
        ';
    }

   location /test1 {
          content_by_lua '
            local hostMap = {["aaa"]="aaa1",["bbb"]="bbb1"}
            ngx.say(hostMap.aaa)
        ';
     }
}