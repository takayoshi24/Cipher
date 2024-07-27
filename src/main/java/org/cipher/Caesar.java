package org.cipher;

public class Caesar implements Cipher{

    private final String digits = "0123456789";
    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`~";
    private final String allCharacters = digits + lowerCaseLetters + upperCaseLetters + specialCharacters;
    private final char[] Alphabet = allCharacters.toCharArray();
    private final int i = Alphabet.length;
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
    private int Check_with_alphabet(char item){
        for (int x = 0; x < Alphabet.length; x++)
            if (item == Alphabet[x]) {
                return x;
            }
        return 0;
    }
    private String transform(String text, int shift) {
        StringBuilder szyfr = new StringBuilder();
        char[] textCharacters = text.toCharArray();

        for (int x = 0; x < text.length(); x++) {
            if(Character.toString(textCharacters[x]).equals(" ")){
                szyfr.append(textCharacters[x]);
            }else {
                char change = Alphabet[(Check_with_alphabet(textCharacters[x]) + shift) % i];
                szyfr.append(change);
            }
        }
        return szyfr.toString();
    }
}
