/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Edgar Mosquera
 */
public class RequestDomain {
    
    public String getApplicationPath(FacesContext context) {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
          
        String scheme = request.getScheme();
        String server = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        
        return scheme + "://" + server + ":" + port + path;
    }
}
