package com.rokotov.problems;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Решение задачи Найти id.
 *
 * Задача
 * В сервисе Киношечка зарегистрировано n пользователей. Все пользователи, за исключением двух,
 * в последние два месяца посещали сайт. Нужно определить id пользователей, которые сайт не посещали.
 *
 * Формат ввода
 * Первая строка содержит число n — количество зарегистрированных пользователей. Это целое число в диапазоне от 2 до10^6.
 * Во второй строке через пробел заданы различные n - 2 целых числа. Каждое из них не превосходит n и больше нуля.
 *
 * Формат вывода
 * Нужно в одной строке вывести по возрастанию два пропущенных числа, разделённые пробелом.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        final String separator = " ";
        final Object[] inputData = Files.lines(Paths.get("input.txt"), StandardCharsets.UTF_8).toArray();
        final StringBuilder result = new StringBuilder();
        int totalUserCount = Integer.parseInt(String.valueOf(inputData[0]));

        final Set<Integer> inputIds = Arrays.stream(((String) inputData[1]).split(separator))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        for (int i = 1; i <= totalUserCount; i++) {
            if (!inputIds.contains(i)) {
                result.append(i).append(separator);
            }
        }

        Files.write(Paths.get("output.txt"), Collections.singleton(result.toString()));
    }
}
