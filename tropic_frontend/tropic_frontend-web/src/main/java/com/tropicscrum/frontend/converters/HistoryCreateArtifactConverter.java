/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.converters;

import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.frontend.controllers.view.ArtifactViewBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "historyCreateArtifactConverter")
@Dependent
public class HistoryCreateArtifactConverter implements Converter {

    @Inject
    ArtifactViewBean artifactViewBean;
    
    /**
     * Creates a new instance of HistoryCreateTaskConverter
     */
    public HistoryCreateArtifactConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int numero = Integer.parseInt(value);
                for (History h : artifactViewBean.getSprintSelected().getProject().getHistories()) {
                    if (h.getId() == numero) {
                        return h;
                    }
                }            
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Historia Invalida"));
            }
            return null;
        }        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((History) value).getId());
        }
    }
    
}
