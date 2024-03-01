import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = readFile();
        List<Integer> sortedNumbers = sortedList(numbers);

        System.out.println("Max number - " + sortedNumbers.get(sortedNumbers.size() - 1));
        System.out.println("Min number - " + sortedNumbers.get(0));
        System.out.println("Median - " + median(sortedNumbers));
        System.out.println("Average - " + average(numbers));

        List<Integer> increasingSequence =  maxIncreasingSequenceOfNumbers(numbers);
        List<Integer> decreasingSequence =   maxDecreasingSequenceOfNumbers(numbers);

        System.out.println("The largest sequence of numbers that increases - " + increasingSequence + " , size - " + increasingSequence.size());
        System.out.println("The largest decreasing sequence of numbers - " + decreasingSequence + " , size - " + decreasingSequence.size());
    }

    private static int median(List<Integer> sortedNumbers) {
        int size = sortedNumbers.size();
        if (size % 2 == 1) {
            return sortedNumbers.get((size - 1) / 2);
        } else {
            return (sortedNumbers.get(size / 2) + sortedNumbers.get(size / 2 - 1)) / 2;
        }
    }

    private static double average(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    private static List<Integer> maxIncreasingSequenceOfNumbers(List<Integer> numbers) {
        return sequenceOfNumbers(numbers, true);
    }

    private static List<Integer>  maxDecreasingSequenceOfNumbers(List<Integer> numbers) {
        return sequenceOfNumbers(numbers, false);
    }

    private static List<Integer> sequenceOfNumbers(List<Integer> numbers, boolean increasing) {
        int maxSequenceLength = 1;
        int endIndex = 0;
        int num = numbers.get(0);
        int sequenceLength = 1;
        for (int i = 1; i < numbers.size(); i++) {
            if ((num < numbers.get(i)) == increasing) {
                sequenceLength++;
            } else {
                if (maxSequenceLength < sequenceLength) {
                    maxSequenceLength = sequenceLength;
                    endIndex = i;
                }
                sequenceLength = 1;
            }
            num = numbers.get(i);
        }
        if (maxSequenceLength < sequenceLength) {
            maxSequenceLength = sequenceLength;
            endIndex = numbers.size() - 1;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = endIndex - maxSequenceLength; i < endIndex; i++) {
            result.add(numbers.get(i));
        }
        return result;
    }

    private static List<Integer> readFile() {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/10m.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    private static List<Integer> sortedList(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .toList();
    }
}