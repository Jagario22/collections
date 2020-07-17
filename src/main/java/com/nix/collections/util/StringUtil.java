package com.nix.collections.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {
    private StringUtil() {
    }

    public static long getNumFromString(List<String> string) {
        String result = string.stream()
                .map(s-> s.codePoints()
                        .mapToObj(i-> (char) i)
                        .filter(Character::isDigit)
                        .map(String::valueOf)
                        .collect(Collectors.joining("")))
                .collect(Collectors.joining(""));
        return Long.parseLong(result);
    }
}
