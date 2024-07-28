package org.cipher;

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
        return transform(text, keyMap,false);
    }

    @Override
    public String decode(String text) {
        return transform(text, keyMap,true);
    }

    public Map getKeyMap(){
        return this.keyMap;
    }
    private Character getKeyByValue(Map<Character, Character> map, Character value) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Return null if no matching key is found
    }
    private String transform(String text, Map<Character, Character> map, boolean flag) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (flag) {
                if (map.containsKey(c)) {
                    result.append(getKeyByValue(map, c));
                } else {
                    result.append(c);
                }
            } else {
                if (map.containsKey(c)) {
                    result.append(map.get(c));
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
            char a = alphabet.charAt(i);
            char s = shuffledAlphabet.get(i);
            keyMap.put(alphabet.charAt(i), shuffledAlphabet.get(i));

        }
    }
}
