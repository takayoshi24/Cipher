package com.github._takayoshi24;

import java.util.*;

public class Monoalphabetic implements Cipher{

    private final String digits = "0123456789";
    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private final String alphabet = digits + lowerCaseLetters + upperCaseLetters + specialCharacters;
    private Map<Character, Character> keyMap;

    public Monoalphabetic(){
        keyMap = new HashMap<>();
        generateKey();
    }

    public Monoalphabetic(Map keyMap){
        this.keyMap = keyMap;
    }

    @Override
    public String encode(String text) {
        return transform(text, false);
    }

    @Override
    public String decode(String text) {
        return transform(text, true);
    }

    public Map getKeyMap(){
        return this.keyMap;
    }
    private Character getKeyByValue(Character value) {
        for (Map.Entry<Character, Character> entry : keyMap.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Return null if no matching key is found
    }
    private String transform(String text,boolean flag) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (flag) {
                if (keyMap.containsKey(c)) {
                    result.append(getKeyByValue(c));
                } else {
                    result.append(c);
                }
            } else {
                if (keyMap.containsKey(c)) {
                    result.append(keyMap.get(c));
                } else {
                    result.append(c);
                }
            }
        }
            return result.toString();
    }

    private void generateKey() {
        List<Character> shuffledAlphabet = new ArrayList<>();
        for (char c : alphabet.toCharArray()) {
            shuffledAlphabet.add(c);
        }
        Collections.shuffle(shuffledAlphabet);
        for (int i = 0; i < alphabet.length(); i++) {
            keyMap.put(alphabet.charAt(i), shuffledAlphabet.get(i));

        }
    }
}
