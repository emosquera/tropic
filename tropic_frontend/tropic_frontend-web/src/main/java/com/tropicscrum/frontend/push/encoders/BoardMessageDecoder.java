/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.push.encoders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.frontend.push.model.BoardMessage;
import java.io.IOException;
import org.primefaces.push.Decoder;

/**
 *
 * @author Edgar Mosquera
 */
public class BoardMessageDecoder implements Decoder<String,BoardMessage>{

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public BoardMessage decode(String u) {
        try {
            return mapper.readValue(u, BoardMessage.class);
        } catch (IOException ex) {
            return new BoardMessage();
        }
    }
    
}
