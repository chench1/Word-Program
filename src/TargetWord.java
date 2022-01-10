/*
Author - Charles Chen
Date - 12/27/2021
Purpose - Finds the number of times a word occurs in a given string
*/

import java.util.Scanner;

public class TargetWord {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //main loop
        do {
            String sentence;
            // validates sentence length
            while (true) {
                System.out.println("Please enter a sentence at least 30 characters in length");
                sentence = scan.nextLine();
                if (sentence.length() < 30) {
                    System.out.println("This sentence was less than 30 characters.");
                    continue;
                }
                break;
            }

            // gets word from user
            String sentenceL = sentence.toLowerCase();
            System.out.println("Enter a word");
            String word = scan.nextLine();
            String wordL = word.toLowerCase();
            int occurrences = findNumberOfOccurrences(sentenceL, wordL, 0);
            int firstOccurrence = findIndexOfWord(sentenceL, wordL, 0);

            if (occurrences != 0) {
                // output
                System.out.println("For the sentence: " + sentence + "\nThe target word of: " + word +
                        "\nThe word " + word + " appeared " + occurrences + " times\nThe first appearance was at index " +
                        firstOccurrence);
            } else {
                System.out.println("For the sentence: " + sentence + "\nThe target word of: " + word +
                        "\nThe word " + word + " appeared " + occurrences + " times\nThe first appearance was never");
            }
            System.out.println("Would you like to continue? Type \"no\" to exit or anything else to continue.");

        } while (!scan.nextLine().equals("no"));

        System.out.println("The program is terminating");
    }

    // recursive method to find number of occurrences
    public static int findNumberOfOccurrences(String sentence, String word, int startIndex) {
        if (sentence.length() == word.length()) {
            return (sentence.equals(word)) ?  1 : 0;
        }

        int index = findIndexOfWord(sentence, word, startIndex);

        // end case
        if (index == -1) {
            return 0;
        }
        return 1 + findNumberOfOccurrences(sentence, word, index + 1);
    }

    // finds the index of the word
    public static int findIndexOfWord(String sentence, String word, int startIndex) {
        for (int i = startIndex; i < sentence.length() - word.length() + 1; i++) {
            int j = i + word.length();
            String sub = sentence.substring(i, j);
            if (sub.equals(word) && isWord(sentence, i, j)) {
                return i;
            }
        }
        return -1;
    }

    // checks if the substring is a word
    public static boolean isWord(String sentence, int i, int j) {
        boolean punctuation = sentence.charAt(j) == '.' || sentence.charAt(j) == '?';
        if (i == 0) {
            return Character.isWhitespace(sentence.charAt(j)) || punctuation;
        }
        boolean whitespaceBefore = Character.isWhitespace(sentence.charAt(i - 1));
        if (j == sentence.length() - 1) {
            return whitespaceBefore;
        }
        if (j != sentence.length() - 1) {
            return whitespaceBefore && (Character.isWhitespace(sentence.charAt(j)) || punctuation);
        } else {
            return whitespaceBefore;
        }
    }
}
