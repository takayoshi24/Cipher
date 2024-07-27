package org.cipher;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String test = "Lecimy grac panowie! GO!! 52S";
        logger.info(test);
        Caesar caesar = new Caesar(5);
        String encode = caesar.encode(test);
        logger.info(encode);
        String decode = caesar.decode(encode);
        logger.info(decode);


    }

}