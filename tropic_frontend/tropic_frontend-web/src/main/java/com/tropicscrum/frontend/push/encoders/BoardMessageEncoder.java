/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.frontend.push.model.BoardMessage;
import org.primefaces.push.Encoder;

/**
 *
 * @author Edgar Mosquera
 */
public class BoardMessageEncoder implements Encoder<BoardMessage, String>{

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public String encode(BoardMessage u) {
        try {
            return mapper.writeValueAsString(u);
        } catch (JsonProcessingException ex) {
            return "";
        }
    }
    
}
