package org.cipher;

import java.util.Random;

public class Vigenere implements Cipher{

    private final String digits = "0123456789";
    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private final String alphabet = digits + lowerCaseLetters + upperCaseLetters + specialCharacters;
    private final int x = alphabet.length();
    private char[][] vigenereTable;
    private String keyword;

    public Vigenere() {
        generateVigenereTable();
    }

    public Vigenere(String keyword) {
        this.keyword = keyword;
        generateVigenereTable();
    }

    public String getKeyword(){
        return this.keyword;
    }

    @Override
    public String encode(String text) {
        return transform(text, true);
    }

    @Override
    public String decode(String text) {
        return transform(text, false);
    }

    private String transform(String text, boolean encode) {
        if(keyword == null){
            generateRandomKeyword(text.length());
        }
        StringBuilder result = new StringBuilder();
        int keywordIndex = 0;

        for (char c : text.toCharArray()) {
            if(!Character.toString(c).equals(" ")) {
                char keyChar = keyword.charAt(keywordIndex % keyword.length());
                if (encode) {
                    result.append(vigenereTable[alphabet.indexOf(keyChar)][alphabet.indexOf(c)]);
                } else {
                    result.append(decodeCharacter(keyChar, c));
                }
                keywordIndex++;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private void generateRandomKeyword(int length) {
        StringBuilder keyword = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            keyword.append(alphabet.charAt(index));
        }

        this.keyword = keyword.toString();
    }

    private void generateVigenereTable() {
        char[][] table = new char[x][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                table[i][j] = alphabet.charAt((i + j) % x);
            }
        }
        vigenereTable = table;
    }
    private char decodeCharacter(char keyChar, char cipherChar) {
        int keyIndex = alphabet.indexOf(keyChar);
        for (int i = 0; i < alphabet.length(); i++) {
            if (vigenereTable[keyIndex][i] == cipherChar) {
                return alphabet.charAt(i);
            }
        }
        return cipherChar;
    }
}
