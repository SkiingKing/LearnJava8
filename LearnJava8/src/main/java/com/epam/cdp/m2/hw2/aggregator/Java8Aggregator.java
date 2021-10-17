package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.util.Pair;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> result = new ArrayList<>();
        TreeMap<String, Long> temp = new TreeMap<String, Long>();
        for (String word : words) {
            temp.put(word, 1L);
        }
        temp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        List<String> keys = new ArrayList<>(temp.keySet());
        List<Long> values = new ArrayList<>(temp.values());
        if (keys.size() < limit)
            limit = keys.size();

        for (int i = 0; i < limit; i++) {
            result.add(new Pair<>(keys.get(i), values.get(i)));
        }
        return result;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }
}