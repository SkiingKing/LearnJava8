package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;


import javafx.util.Pair;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> result = new ArrayList<>();

        Map<String, Integer > wordCounter = words.stream()
                .collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));

        for(Map.Entry<String, Integer> value : wordCounter.entrySet()) {
            result.add(new Pair<>(value.getKey(), Long.valueOf(value.getValue())));
        }
        return result.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        TreeSet<String> seen = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        List<String> result = new ArrayList<>(words).stream()
                .filter(item -> !seen.add(item))
                .map(i -> i.toUpperCase(Locale.ROOT)).sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (o1.length() == o2.length())
                            return o1.compareTo(o2);
                        if (o1.length() > o2.length())
                            return 1;
                        if (o1.length() < o2.length())
                            return -1;
                        return 0;
                    }
                }).collect(Collectors.toList());
        return new ArrayList<>(result).stream().limit(limit).collect(Collectors.toList());
    }
}