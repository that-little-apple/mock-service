package test;

import com.example.mockservice.HttpDataApplication;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//当启动类与测试类包名不一致时，需要显示指定启动类，否则可以不指定
@SpringBootTest(classes = HttpDataApplication.class)
public class junit5Tests {
    @BeforeAll
    static void beforeAll() {
        System.out.println("在所有test方法执行前执行 @BeforeAll注解方法");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("在所有test方法执行后执行 @AfterAll注解方法");
    }

    @BeforeEach
    void beforeEachTest() throws Exception {
        System.out.println("在每个test方法执行前执行 @BeforeEach注解方法");
    }

    @AfterEach
    void afterEachTest() throws Exception {
        System.out.println("在每个test方法执行后执行 @AfterEach注解方法");
    }

    @Test
    @DisplayName("执行aTest方法")
    void aTest() {
        assertEquals(4, (2 + 2));
    }

    @Test
    @DisplayName("assert all test")
    void assertTest() {
        String expected = "chenpp";
        String actual = "chen" + "pp";
        String nullValue = null;
        assertEquals(expected, actual, "incorrect!");
        assertNull(nullValue);
    }

    @Test
    void assertAllTestWhenOneIsError() {
        String expected = "Hi,chenpp";
        String actual = "Hi," + "chenpp";
        String nullValue = null;
        assertAll(
                "Assert All of these",
                () -> assertEquals(expected, "", "incorrect!"),
                () -> assertFalse(nullValue != null),
                () -> assertNull(nullValue),
                () -> assertNull("not null", "incorrect!"),
                () -> assertTrue(nullValue == null));
    }
}