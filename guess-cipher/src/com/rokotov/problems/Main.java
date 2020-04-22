package com.rokotov.problems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Решение задачи Разгадай шифр.
 * <p>
 * Задача
 * Шерлок Холмс и доктор Ватсон передают друг другу зашифрованные сообщения, состоящие из чисел.
 * Каждое число может обозначать одну букву, слово или знак препинания.
 * Получая последовательность чисел, Холмс и Ватсон могут расшифровывать сообщения друг друга.
 * Однако они стали подозревать, что кто-то разгадал их шифр и повысили уровень безопасности.
 * Теперь каждое сообщение зашифровано в матрице.
 * Чтобы его прочитать, нужно распечатать значения матрицы по спирали, начиная от центра вверх и далее по часовой стрелке.
 * <p>
 * Формат ввода
 * Первая строка содержит целое нечётное число m в диапазоне от 1 до 1000 — количество строк и столбцов матрицы.
 * В каждой из следующих m строк даны m целых чисел в диапазоне от -2 147 483 648 до 2 147 483 647, разделённых пробелом.
 * <p>
 * Формат вывода
 * Нужно вывести значения в матрице, начиная с центра по спирали.
 * Движение вверх, далее по часовой стрелке. Каждое число выводится в отдельной строке.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        final String inputSeparator = " ";
        final String outputSeparator = "\n";

        final StringBuilder result = new StringBuilder();
        final Object[] inputData = Files.lines(Paths.get("input.txt")).toArray();

        int size = Integer.parseInt(String.valueOf(inputData[0]));
        int[][] arrayData = new int[size][size];

        for (int i = 0; i < size; i++) {
            List<Integer> collect = Arrays.stream(((String) inputData[i + 1]).split(inputSeparator))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (int j = 0; j < collect.size(); j++) {
                arrayData[i][j] = collect.get(j);
            }
        }

        if (size > 1) {
            int center = size / 2;
            result.append(arrayData[center][center]).append(outputSeparator);

            for (int iteration = 0; iteration < center; iteration++) {

                int up = center - iteration - 1;
                for (int i = up + 1; i < center + iteration + 1; i++) {
                    result.append(arrayData[up][i]).append(outputSeparator);
                }

                int right = center + iteration + 1;
                for (int i = up; i < center + iteration + 1; i++) {
                    result.append(arrayData[i][right]).append(outputSeparator);
                }

                for (int i = right; i >= center - iteration; i--) {
                    result.append(arrayData[right][i]).append(outputSeparator);
                }

                for (int i = right; i >= center - iteration - 1; i--) {
                    result.append(arrayData[i][up]).append(outputSeparator);
                }
            }
        } else {
            result.append(arrayData[0][0]);
        }

        Files.write(Paths.get("output.txt"), Collections.singleton(result.toString()));
    }
}
