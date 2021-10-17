package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.util.Pair;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer item : numbers) {
            sum += item;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> result = new ArrayList<>();
        TreeMap<String, Long> frequencyMap = new TreeMap<>();
        long frequency = 0L;
        long localLimit = limit;

        if (words.isEmpty())
            return Collections.emptyList();

        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (words.get(j).equals(words.get(i)))
                    frequency++;
            }
            frequencyMap.put(words.get(i), frequency);
            frequency = 0L;
        }
        List<String> keys = new ArrayList<>(frequencyMap.keySet());
        List<Long> values = new ArrayList<>(frequencyMap.values());
        if (keys.size() < limit)
            localLimit = keys.size();

        for (int i = 0; i < localLimit; i++) {
            result.add(new Pair<>(keys.get(i), values.get(i)));
        }
        return result;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        List<String> duplicateWords = new ArrayList<>();

        if (words.isEmpty())
            return Collections.emptyList();

        for (int i = 0; i < words.size(); i++) {
            for (int j = i; j < words.size(); j++) {
                if (words.get(i).equalsIgnoreCase(words.get(j)) && i != j) {
                    duplicateWords.add(words.get(i).toUpperCase(Locale.ROOT));
                }
                if (duplicateWords.size() == limit) break;
            }
        }

        duplicateWords.sort(Comparator.comparingInt(String::length));

        return duplicateWords;
    }
}
