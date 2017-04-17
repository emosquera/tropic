/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.converters;

import com.tropicscrum.backend.client.model.Artifact;
import com.tropicscrum.frontend.controllers.view.TaskViewBean;
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
 * @author syslife02
 */
@Named(value = "artifactTaskConverter")
@Dependent
public class ArtifactTaskConverter implements Converter{

    @Inject
    TaskViewBean taskViewBean;
    
    /**
     * Creates a new instance of ArtifactTaskConverter
     */
    public ArtifactTaskConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int numero = Integer.parseInt(value);
                for (Artifact a : taskViewBean.getMilestoneSelected().getArtifacts()) {
                    if (a.getId() == numero) {
                        return a;
                    }
                }            
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Componente Invalido"));
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Artifact) value).getId());
        }
    }
    
}
