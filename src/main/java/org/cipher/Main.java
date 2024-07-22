package org.cipher;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String test = "Witamy w grze o szachy";
       Caesar(test, 3);
        //Check_with_alphabet('c');

    }
    static final char[] Aplhabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static int i = Aplhabet.length;

    static String Caesar(String text, int k){
    StringBuilder sb = new StringBuilder();
    char[] text_characters = text.toCharArray();

    // wskaźniki pomocnicze
    logger.info(Arrays.toString(text_characters));
    int text_length = text.length();

    //pętla na szyfr
    for (int x = 0; x < text.length(); x++) {
        if(Character.toString(text_characters[x]).equals(" ")){
            sb.append(text_characters[x]);
        }else {
            char change = Aplhabet[(Check_with_alphabet(text_characters[x]) + k) % i];
            sb.append(change);
            logger.info(sb.toString());
        }
        }

        return sb.toString();
    }
    public static int Check_with_alphabet(char item){
        for (int x = 0; x < Aplhabet.length;x++){
            if(item == Aplhabet[x] || item == Character.toUpperCase(Aplhabet[x])){
                logger.info(String.valueOf(x));
                return x;
            }
        }
        return 0;
    }

}