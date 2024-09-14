package com.theruzil.shorter.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

@SpringBootTest
class StringConvertServiceImplTest {
    @Autowired
    StringConvertServiceImpl shortService;

    @ParameterizedTest
    @MethodSource("idToStringSamples")
    void idToString(String expected, int input, boolean error) {
        if (error) {
            assertThrows(
                    jakarta.validation.ConstraintViolationException.class,
                    () -> shortService.idToString(input)
            );
        } else {
            String result = shortService.idToString(input);
            assert expected.equals(result);
        }
    }

    @ParameterizedTest
    @MethodSource("stringToIdSamples")
    void stringToId(String input, int expected, boolean error) {
        if (error) {
            assertThrows(
                    jakarta.validation.ConstraintViolationException.class,
                    () -> shortService.stringToId(input)
            );
        } else {
            int result = shortService.stringToId(input);
            assert expected == result;
        }
    }

    private static Stream<Arguments> idToStringSamples() {
        return Stream.of(
                Arguments.of("", Integer.MIN_VALUE, true),
                Arguments.of("", -123, true),
                Arguments.of("", 0, true),
                Arguments.of("b9", 123, false),
                Arguments.of("qi", 1000, false),
                Arguments.of("emjc", 1_000_000, false),
                Arguments.of("cvuMLb", Integer.MAX_VALUE, false)
        );
    }

    private static Stream<Arguments> stringToIdSamples() {
        return Stream.of(
                Arguments.of("", 0, true),
                Arguments.of(null, 0, true),
                Arguments.of("b9", 123, false),
                Arguments.of("qi", 1000, false),
                Arguments.of("emjc", 1_000_000, false),
                Arguments.of("cvuMLb", Integer.MAX_VALUE, false)
        );
    }
}