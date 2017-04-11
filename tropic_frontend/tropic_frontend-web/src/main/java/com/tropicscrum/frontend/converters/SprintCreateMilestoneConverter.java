/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.converters;

import com.tropicscrum.backend.client.model.Sprint;
import com.tropicscrum.frontend.controllers.view.MilestoneViewBean;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "sprintCreateMilestoneConverter")
@Dependent
public class SprintCreateMilestoneConverter implements Converter {
    @Inject
    MilestoneViewBean milestoneViewBean;
    
    public SprintCreateMilestoneConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            try {
                int numero = Integer.parseInt(value);
                for (Sprint s : milestoneViewBean.getMySprints()) {
                    if (s.getId() == numero) {
                        return s;
                    }
                }            
            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Sprint Invalido"));
            }
            return null;
        }        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Sprint) value).getId());
        }
    }
}
