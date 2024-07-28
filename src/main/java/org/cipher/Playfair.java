package org.cipher;

public class Playfair implements Cipher{

    private final String digits = "0123456789";
    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private final String alphabet = digits + lowerCaseLetters + upperCaseLetters + specialCharacters;

    public Playfair() {
    }

    public Playfair(String keyword) {
    }

    @Override
    public String encode(String text) {
        return "";
    }

    @Override
    public String decode(String text) {
        return "";
    }
}
