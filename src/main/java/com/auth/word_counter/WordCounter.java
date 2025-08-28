package com.auth.word_counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        String nLine = br.readLine();

        if (text == null || nLine == null) {
            System.err.println("Ожидаются две строки ввода: текст и число n.");
            return;
        }

        int n;
        try {
            n = Integer.parseInt(nLine.trim());
        } catch (NumberFormatException e) {
            System.err.println("Вторая строка должна быть целым числом.");
            return;
        }

        List<String> words = Arrays.stream(text.toLowerCase(Locale.ROOT).split("\\P{L}+"))
                .filter(w -> !w.isEmpty())
                .toList();

        Map<String, Long> freq = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<String> result = freq.entrySet().stream()
                .filter(e -> e.getValue() == n)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();

        System.out.println(String.join(" ", result));
    }

}
