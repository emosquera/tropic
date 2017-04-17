/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.session;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "menuSessionBean")
@SessionScoped
public class MenuSessionBean implements Serializable {

    private MenuModel model;
    
    /**
     * Creates a new instance of MenuSessionBean
     */
    public MenuSessionBean() {
    }

    public MenuModel getModel() {
        return model;
    }

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
         
        DefaultMenuItem item = new DefaultMenuItem("Dashboard");
        item.setOutcome("/home/home");
        item.setIcon("dashboard-icon");
        model.addElement(item);
        
        //Definiciones submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Definiciones");
        firstSubmenu.setStyleClass("yellowMenuItem");
         
        item = new DefaultMenuItem("Tecnologias");
        item.setOutcome("/home/home");
        item.setIcon("collaborator-icon");
        firstSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Proyectos");
        item.setOutcome("/home/projects");     
        item.setIcon("product-icon");
        firstSubmenu.addElement(item);                

        item = new DefaultMenuItem("Historias");
        item.setOutcome("/home/history");
        item.setIcon("history-icon");
        firstSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Sprints");
        item.setOutcome("/home/sprint");
        item.setIcon("sprint-icon");
        firstSubmenu.addElement(item);                
        
        item = new DefaultMenuItem("Hitos");
        item.setOutcome("/home/milestone");
        item.setIcon("milestone-icon");
        firstSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Componentes");
        item.setOutcome("/home/artifact");
        item.setIcon("team-icon");
        firstSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Tareas");
        item.setOutcome("/home/task");
        item.setIcon("task-icon");
        firstSubmenu.addElement(item);
        
        model.addElement(firstSubmenu);
         
        //Scrum submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Scrum");
        secondSubmenu.setStyleClass("blueMenuItem");
 
        item = new DefaultMenuItem("Estimaciones");
        item.setOutcome("/home/sprints"); 
        item.setIcon("poker-icon");
        item.setParam("page", "poker");
        secondSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Dailys");
        item.setOutcome("/home/home"); 
        item.setIcon("daily-icon");
        secondSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Actividad");
        item.setOutcome("/home/sprints"); 
        item.setIcon("process-icon");
        item.setParam("page", "board");
        secondSubmenu.addElement(item);
 
        model.addElement(secondSubmenu);
        
        //Grafichs submenu
        DefaultSubMenu thirdSubmenu = new DefaultSubMenu("Graficos");
        thirdSubmenu.setStyleClass("greenMenuItem");
 
        item = new DefaultMenuItem("Burndown Chart (Tareas)");
        item.setOutcome("/home/home"); 
        item.setIcon("burndown-task-icon");
        thirdSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Burndown Chart (Horas)");
        item.setOutcome("/home/home"); 
        item.setIcon("burndown-hour-icon");
        thirdSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Detalle de Ejecucion");
        item.setOutcome("/home/home"); 
        item.setIcon("detail-plan-icon");
        thirdSubmenu.addElement(item);
 
        model.addElement(thirdSubmenu);        
    }
    
}
