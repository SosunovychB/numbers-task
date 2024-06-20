package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/bohdan/Desktop/Java/NumbersTask/src/main/resources/10m.txt"); // Замініть на шлях до вашого файлу
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        }

        // Визначення максимального і мінімального числа
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);

        // Обчислення середнього арифметичного значення
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);

        // Пошук найбільшої зростаючої і зменшувальної послідовності (рішення коригувалось chat gpt)
        List<Integer> longestIncreasingSequence = findLongestIncreasingSequence(numbers);
        List<Integer> longestDecreasingSequence = findLongestDecreasingSequence(numbers);

        // Обчислення медіани
        Collections.sort(numbers);
        double median;
        int n = numbers.size();
        if (n % 2 == 0) {
            median = ((double) numbers.get(n / 2 - 1) + numbers.get(n / 2)) / 2;
        } else {
            median = numbers.get(n / 2);
        }

        // Виведення результатів
        System.out.println("Максимальне число: " + max);
        System.out.println("Мінімальне число: " + min);
        System.out.println("Середнє арифметичне значення: " + average);
        System.out.println("Медіана: " + median);
        System.out.println("Найбільша зростаюча послідовність: " + longestIncreasingSequence);
        System.out.println("Найбільша зменшувальна послідовність: " + longestDecreasingSequence);
    }

    private static List<Integer> findLongestIncreasingSequence(List<Integer> numbers) {
        List<Integer> longest = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0 || numbers.get(i) > numbers.get(i - 1)) {
                current.add(numbers.get(i));
            } else {
                if (current.size() > longest.size()) {
                    longest = new ArrayList<>(current);
                }
                current.clear();
                current.add(numbers.get(i));
            }
        }

        if (current.size() > longest.size()) {
            longest = new ArrayList<>(current);
        }

        return longest;
    }

    private static List<Integer> findLongestDecreasingSequence(List<Integer> numbers) {
        List<Integer> longest = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0 || numbers.get(i) < numbers.get(i - 1)) {
                current.add(numbers.get(i));
            } else {
                if (current.size() > longest.size()) {
                    longest = new ArrayList<>(current);
                }
                current.clear();
                current.add(numbers.get(i));
            }
        }

        if (current.size() > longest.size()) {
            longest = new ArrayList<>(current);
        }

        return longest;
    }
}
