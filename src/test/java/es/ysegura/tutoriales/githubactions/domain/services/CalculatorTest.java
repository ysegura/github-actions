package es.ysegura.tutoriales.githubactions.domain.services;

import es.ysegura.tutoriales.githubactions.domain.vo.Result;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest
    @MethodSource("addProvider")
    void additionShouldReturnCorrectResult(int a, int b, Result result) {
        assertEquals(calculator.add(a, b), result);
    }

    @ParameterizedTest
    @MethodSource("subtractProvider")
    void subtractShouldReturnCorrectResult(int a, int b, Result result) {
        assertEquals(calculator.subtract(a, b), result);
    }

    @ParameterizedTest
    @MethodSource("multiplyProvider")
    void multiplyShouldReturnCorrectResult(int a, int b, Result result) {
        assertEquals(calculator.multiply(a, b), result);
    }

    @ParameterizedTest
    @MethodSource("divideProvider")
    void divideShouldReturnCorrectResult(int a, int b, Result result) {
        assertEquals(calculator.divide(a, b), result);
    }

    private static Stream<Arguments> addProvider() {
        return
                Stream.of(
                        Arguments.of(1, 0, new Result(1)),
                        Arguments.of(1, 1, new Result(2)),
                        Arguments.of(1, 2, new Result(3)),
                        Arguments.of(1, 3, new Result(4)),
                        Arguments.of(1, 4, new Result(5))
                );
    }

    private static Stream<Arguments> subtractProvider() {
        return
                Stream.of(
                        Arguments.of(1, 0, new Result(1)),
                        Arguments.of(1, 1, new Result(0)),
                        Arguments.of(1, 2, new Result(-1)),
                        Arguments.of(3, 1, new Result(2)),
                        Arguments.of(0, 0, new Result(0))
                );
    }

    private static Stream<Arguments> multiplyProvider() {
        return
                Stream.of(
                        Arguments.of(1, 0, new Result(0)),
                        Arguments.of(1, 1, new Result(1)),
                        Arguments.of(1, 2, new Result(2)),
                        Arguments.of(1, 3, new Result(3)),
                        Arguments.of(1, 4, new Result(4))
                );
    }

    private static Stream<Arguments> divideProvider() {
        return
                Stream.of(
                        Arguments.of(1, 1, new Result(1)),
                        Arguments.of(2, 1, new Result(2)),
                        Arguments.of(3, 2, new Result(1)),
                        Arguments.of(4, 2, new Result(2)),
                        Arguments.of(10, 5, new Result(2))
                );
    }

}