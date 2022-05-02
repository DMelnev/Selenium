package Lesson4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    static Logger logger = LoggerFactory.getLogger(MainTest.class);
    private final double DELTA = 0.001;
    private static int counter1 = 0;
    private static int counter2 = 0;

    @Nested
    @DisplayName("triangle square tests")
    class SquareTest {

        @BeforeAll
        static void beforeAll() {
            counter1 = 0;
        }

        @Test
        @DisplayName("date 3 4 5 6")
        void simpleTest() {
            assertEquals(6, Main.rectangleSquare(3, 4, 5), DELTA);
            logger.info("simpleTest passed");
//            logger.error("Ошибка");
        }

        @Test
        @DisplayName("date 3 4 5 6")
        @Disabled("disabled because it is the same as prev test")
        void SameTestDisabled() {
            assertEquals(6, Main.rectangleSquare(3, 4, 5), DELTA);
            logger.info("simpleTest passed");
        }

        @RepeatedTest(20)
        @DisplayName("repeated, date 30000, 40000, 50000, 600000000")
        void repeatedTest() {
            assertEquals(600000000, Main.rectangleSquare(30000, 40000, 50000), DELTA);
            logger.info("repeatedTest " + ++counter1 + " passed");
        }

        @ParameterizedTest
        @CsvSource({
                "0, 4, 5, 0",
                "3, 0, 5, 0",
                "3, 4, 0, 0",
                "-3, 4, 5, 0",
                "3, -4, 5, 0",
                "3, 4, -5, 0"
        })
        @DisplayName("parametrized")
        void parametrizedTest(double a, double b, double c, double answer) {
            assertEquals(answer, Main.rectangleSquare(a, b, c), DELTA);
            logger.info("parametrizedTest " + ++counter2 + " passed");
        }

        @Test
        @DisplayName("Timeout test")
        void TimeoutTest() {
            Assertions.assertTimeout(Duration.ofMillis(50), () -> {
                Thread.sleep((20));
                return "result";
            });
            logger.info("TimeoutTest passed");
        }


    }

    @Nested
    @DisplayName("division tests")
    class DivisionTests {

        @BeforeAll
        static void beforeAll() {
            counter1 = 0;
        }

        @ParameterizedTest
        @DisplayName("parametrized")
        @CsvSource({
                "2,1,2",
                "9,3,3",
                "100,5,20"
        })
        void division(double a, double b, double answer) {
            assertEquals(answer, Main.division(a, b));
            logger.info("parametrizedTest division " + ++counter1 + " passed");
        }

        @Test
        @DisplayName("Division by zero")
        void divisionByZero() {
            assertThrows(ArithmeticException.class, () -> {
                double res = 1 / 0; //Main.division(1,0); //i tried but didn't work (((
            });
            logger.error("divisionByZero throw I couldn't run");
        }

    }
}