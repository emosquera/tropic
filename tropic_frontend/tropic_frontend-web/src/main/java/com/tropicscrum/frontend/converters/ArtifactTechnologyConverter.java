/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.converters;

import com.tropicscrum.backend.client.model.Technology;
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
 * @author syslife02
 */
@Named(value = "artifactTechnologyConverter")
@Dependent
public class ArtifactTechnologyConverter implements Converter{

    @Inject
    ArtifactViewBean artifactViewBean;
    
    /**
     * Creates a new instance of ArtifactTechnologyConverter
     */
    public ArtifactTechnologyConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int numero = Integer.parseInt(value);
                for (Technology t : artifactViewBean.getTechnologies()) {
                    if (t.getId() == numero) {
                        return t;
                    }
                }            
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Tecnologia Invalida"));
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Technology) value).getId());
        }
    }
    
}
