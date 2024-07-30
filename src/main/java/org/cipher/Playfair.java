package org.cipher;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Playfair implements Cipher{

    private final String digits = "0123456789";
    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private final String chineseCharacters = "和爱生学中 ";
    private final char chineseChar = '中'; // Chinese character for boger character
    private final String alphabet = digits + lowerCaseLetters + upperCaseLetters + specialCharacters + chineseCharacters;
    private char[][] matrix;
    private String keyword;
    private final int modulo = 10;
    private final int moduloCorner = 9;
    public Playfair() {
        genereteKeyword();
        this.matrix = generateMatrix();
    }

    public Playfair(String keyword) {
        this.keyword = keyword;
        this.matrix = generateMatrix();
    }

    @Override
    public String encode(String text) {
        text = prepareText(text);
        StringBuilder encoded = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            char a = text.charAt(i);
            char b = (i + 1 < text.length() ? text.charAt(i + 1) : chineseChar);

                int[] posA = findPosition(a);
                int[] posB = findPosition(b);

                if (posA[0] == posB[0]) {
                    // Same row
                    encoded.append(matrix[posA[0]][(posA[1] + 1) % modulo]);
                    encoded.append(matrix[posB[0]][(posB[1] + 1) % modulo]);
                } else if (posA[1] == posB[1]) {
                    // Same column
                    encoded.append(matrix[(posA[0] + 1) % modulo][posA[1]]);
                    encoded.append(matrix[(posB[0] + 1) % modulo][posB[1]]);
                } else {
                    // Rectangle swap
                    encoded.append(matrix[posA[0]][posB[1]]);
                    encoded.append(matrix[posB[0]][posA[1]]);
                }
            }

        return encoded.toString();
    }

    @Override
    public String decode(String text) {
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()  ? text.charAt(i + 1) : chineseChar);

                int[] posA = findPosition(a);
                int[] posB = findPosition(b);

                if (posA[0] == posB[0]) {
                    // Same row
                    decoded.append(matrix[posA[0]][(posA[1] + moduloCorner) % modulo]);
                    decoded.append(matrix[posB[0]][(posB[1] + moduloCorner) % modulo]);
                } else if (posA[1] == posB[1]) {
                    // Same column
                    decoded.append(matrix[(posA[0] + moduloCorner) % modulo][posA[1]]);
                    decoded.append(matrix[(posB[0] + moduloCorner) % modulo][posB[1]]);
                } else {
                    // Rectangle swap
                    decoded.append(matrix[posA[0]][posB[1]]);
                    decoded.append(matrix[posB[0]][posA[1]]);
                }
            }

        return removePadding(decoded.toString());
    }

    private String prepareText(String text) {
        StringBuilder prepared = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (prepared.length() > 0 && prepared.charAt(prepared.length() - 1) == currentChar) {
                    prepared.append(chineseChar);
                }
            prepared.append(currentChar);
        }

        if (prepared.length() % 2 != 0) {
            prepared.append(chineseChar);
        }

        return prepared.toString();
    }

    private char[][] generateMatrix() {
        char[][] matrix = new char[modulo][modulo];
        Set<Character> usedChars = new HashSet<>();
        int index = 0;

        // Fill matrix with keyword
        for (char c : keyword.toCharArray()) {
            if (!usedChars.contains(c) && alphabet.indexOf(c) != -1) {
                matrix[index / modulo][index % modulo] = c;
                usedChars.add(c);
                index++;
            }
        }

        // Fill matrix with remaining letters
        for (char c : alphabet.toCharArray()) {
            if (!usedChars.contains(c)) {
                matrix[index / modulo][index % modulo] = c;
                usedChars.add(c);
                index++;
            }
        }

        return matrix;
    }

    private int[] findPosition(char c) {
        for (int i = 0; i < modulo; i++) {
            for (int j = 0; j < modulo; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should never happen if the matrix is correctly generated
    }

    private String removePadding(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (currentChar == chineseChar) {
                result.append(' ');
            } else {
                result.append(currentChar);
            }
        }

        return result.toString().trim();
    }

    void genereteKeyword(){
        StringBuilder result = new StringBuilder();
        char[] alphabet2 = alphabet.toCharArray();
        Random random = new Random();
        int i = random.nextInt(50);
        for(int x = 0; x <i; x++){
            int randomIndex = random.nextInt(alphabet2.length);
            char randomChar = alphabet2[randomIndex];
            result.append(randomChar);
        }
        this.keyword = result.toString();

    }

    public String returnKeyword(){
        return this.keyword;
    }
}
