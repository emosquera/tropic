/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.converters;

import com.tropicscrum.backend.client.model.Milestone;
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
@Named(value = "milestoneArtifactConverter")
@Dependent
public class MilestoneArtifactConverter implements Converter {

    @Inject
    ArtifactViewBean artifactViewBean;
    
    /**
     * Creates a new instance of MilestoneTaskConverter
     */
    public MilestoneArtifactConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int numero = Integer.parseInt(value);
                for (Milestone m : artifactViewBean.getHistorySelected().getMilestones()) {
                    if (m.getId() == numero) {
                        return m;
                    }
                }            
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Hito Invalido"));
            }
            return null;
        }        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Milestone) value).getId());
        }
    }
    
}
