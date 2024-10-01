package org.cipher;

import java.util.Map;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String test = "Lecimy! na wiadukt 120! *%^@1";
        logger.info("0: "+test);
        Caesar caesar = new Caesar(5);
        String encode = caesar.encode(test);
        //logger.info(encode);
        String decode = caesar.decode(encode);
        logger.info("Caesar: "+ decode);

        Monoalphabetic monoalphabetic = new Monoalphabetic();
        //logger.info(monoalphabetic.getKeyMap().toString());
        String monoalphabetic_encode = monoalphabetic.encode(test);
        //logger.info("0: "+monoalphabetic_encode);
        String monoalphabetic_decode = monoalphabetic.decode(monoalphabetic_encode);
        logger.info("Monoalphabetic: "+monoalphabetic_decode);
        //Map key = monoalphabetic.getKeyMap();
        //Monoalphabetic monoalphabetic2 = new Monoalphabetic(key);
        //String monoalphabetic_decode2 = monoalphabetic2.decode(monoalphabetic_encode);
        //logger.info("2: "+monoalphabetic_decode2);


        Vigenere vigenere = new Vigenere();
        String vinEncode = vigenere.encode(test);
        //logger.info("1: "+vinEncode);
        String vinDecode = vigenere.decode(vinEncode);
        logger.info("Vigenere: "+vinDecode);

        Playfair playfair = new Playfair();
        //logger.info("*: "+ playfair.returnKeyword());
        String playEncode = playfair.encode(test);
        //logger.info("1: "+playEncode);
        String playDecode = playfair.decode(playEncode);
        logger.info("Playfair: "+playDecode);



    }

}