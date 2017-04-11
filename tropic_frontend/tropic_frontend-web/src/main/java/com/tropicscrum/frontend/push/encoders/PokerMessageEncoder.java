/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.encoders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.frontend.push.model.PokerMessage;
import org.primefaces.push.Encoder;

/**
 *
 * @author Edgar Mosquera
 */
public final class PokerMessageEncoder implements Encoder<PokerMessage, String> {

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public String encode(PokerMessage u) {
        try {
            return mapper.writeValueAsString(u);
        } catch (JsonProcessingException ex) {
            return "";
        }
    }
    
}
