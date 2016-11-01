/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.backend.client.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author syslife02
 */
public class Md5Converter {   

    public String StringToMD5(String text) throws NoSuchAlgorithmException {
        MessageDigest encripter = MessageDigest.getInstance("MD5");
        encripter.update(text.getBytes(),0,text.length());
        return new BigInteger(1,encripter.digest()).toString(16);
    }
}
