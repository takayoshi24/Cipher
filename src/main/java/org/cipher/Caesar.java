package org.cipher;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Caesar implements Cipher{

    //class fields
    private final String digits = "0123456789";
    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private final String allCharacters = digits + lowerCaseLetters + upperCaseLetters + specialCharacters;
    private final char[] alphabet = allCharacters.toCharArray();
    private final List<Character> alphabetList = allCharacters.chars().mapToObj(c -> (char) c).collect(Collectors.toList());;
    private final int shift;

    public Caesar(int shift) {
        this.shift = shift;
    }

    @Override
    public String encode(String text) {
        return transform(text, shift);
    }

    @Override
    public String decode(String text) {
        return transform(text, -shift);
    }

    private String transform(String text, int shift) {
        StringBuilder result = new StringBuilder();
        char[] textCharacters = text.toCharArray();

       for(char character: textCharacters) {
            if(character == ' '){
                result.append(character);
            }else {
                char change = alphabet[(alphabetList.indexOf(character) + shift) % alphabet.length];
                result.append(change);
            }
        }
        return result.toString();
    }
}
