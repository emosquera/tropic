/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tropicscrum.frontend.controllers.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tropicscrum.backend.client.exceptions.UpdateException;
import com.tropicscrum.backend.client.facade.TaskFacadeRemote;
import com.tropicscrum.backend.client.model.History;
import com.tropicscrum.backend.client.model.Milestone;
import com.tropicscrum.backend.client.model.SprintVelocity;
import com.tropicscrum.backend.client.model.SprintUser;
import com.tropicscrum.backend.client.model.Task;
import com.tropicscrum.backend.client.model.UserEstimate;
import com.tropicscrum.backend.client.utils.SortMapByValue;
import com.tropicscrum.frontend.controllers.view.PokerViewBean;
import com.tropicscrum.frontend.push.model.PokerMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Edgar Mosquera
 */
@Named(value = "pokerRequestBean")
@RequestScoped
public class pokerRequestBean implements Serializable {

    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    private final static String CHANNEL = "/poker/";

    private Boolean showEstimate = Boolean.FALSE;

    @EJB(lookup = TaskFacadeRemote.JNDI_REMOTE_NAME)
    TaskFacadeRemote taskFacadeRemote;

    @Inject
    PokerViewBean pokerViewBean;

    public Boolean getShowEstimate() {
        return showEstimate;
    }

    public void setShowEstimate(Boolean showEstimate) {
        this.showEstimate = showEstimate;
    }

    private SprintVelocity calculateWinnignPoint() {
        Double sumVotes = 0.0;
        int countVotes = 0;
        Double avgVotes;

        //Primera estrategia de eleccion, elegir la carta mas votada. En caso de empate entre 2 cartas, se elige la menor.
        //Si el empate es entre mas de 2 cartas, se pasa a estrategia de average
        Map<Double, Integer> groupVotes = new HashMap<>();
        for (UserEstimate userEstimate : pokerViewBean.getTaskSelected().getUserEstimates()) {
            if (groupVotes.containsKey(userEstimate.getPoints())) {
                groupVotes.put(userEstimate.getPoints(), groupVotes.get(userEstimate.getPoints()) + 1);
            } else {
                groupVotes.put(userEstimate.getPoints(), 1);
            }
            sumVotes += userEstimate.getPoints();
            countVotes++;
        }

        //Ordenamos la lista desde el mas votado hasta el menos votado
        groupVotes = SortMapByValue.reverseSortMapByValue(groupVotes);

        //Elegimos el primer elelmento de la lista ordenada
        Map.Entry<Double, Integer> mostVoted = groupVotes.entrySet().iterator().next();

        //Eliminamos de la lista los elementos menos votados que el primero
        for (Iterator<Map.Entry<Double, Integer>> i = groupVotes.entrySet().iterator(); i.hasNext();) {
            Map.Entry<Double, Integer> entry = i.next();
            if (!Objects.equals(mostVoted.getValue(), entry.getValue())) {
                i.remove();
            }
        }

        //Si hay mas de 2 elementos con igual numero de votos, se calcula el promedio
        if (groupVotes.size() > 2) {
            SprintVelocity point = new SprintVelocity();
            Double compare = 9999999999.0;
            avgVotes = sumVotes / countVotes;
            for (SprintVelocity pokerRule : pokerViewBean.getSprint().getSprintVelocitys()) {
                if (Math.abs(avgVotes - pokerRule.getPoint()) < compare) {
                    compare = Math.abs(avgVotes - pokerRule.getPoint());
                    point = pokerRule;
                } else {
                    break;
                }
            }
            return point;
        } else { //Se devuelve la menor carta de las mas votadas     
            SprintVelocity point = new SprintVelocity();
            Map<Double, Integer> groupVotesOrdered = new TreeMap<>(groupVotes);
            Map.Entry<Double, Integer> minorVoted = groupVotesOrdered.entrySet().iterator().next();
            for (SprintVelocity pokerRule : pokerViewBean.getSprint().getSprintVelocitys()) {
                if (Objects.equals(pokerRule.getPoint(), minorVoted.getKey())) {
                    point = pokerRule;
                }
            }
            return point;
        }
    }

    public void receiveTaskSprintMessage() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            String jsonMessage = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pokerMessage");
            PokerMessage pokerMessage = mapper.readValue(jsonMessage, PokerMessage.class);
            pokerViewBean.getPokerMessage().setIsVote(pokerMessage.getIsVote());
            pokerViewBean.getPokerMessage().setIsClean(pokerMessage.getIsClean());
            if (pokerMessage.getIsVote()) {
                UserEstimate ue = new UserEstimate();
                for (SprintUser su : pokerViewBean.getSprint().getSprintUsers()) {
                    if (su.getId().equals(Long.parseLong(pokerMessage.getPokerVote().getSprintUser()))) {
                        ue.setSprintUser(su);
                        ue.setTask(pokerViewBean.getTaskSelected());
                        ue.setPoints(pokerMessage.getPokerVote().getSprintVelocity().getPoint());
                        pokerViewBean.getTaskSelected().getUserEstimates().add(ue);
                        break;
                    }
                }
                SprintVelocity winVote = calculateWinnignPoint();
                pokerViewBean.getTaskSelected().setPoints(winVote.getPoint());
                pokerViewBean.getTaskSelected().setEstimatedDuration(winVote.getHours());
            } else if (pokerMessage.getIsTask()) {
                pokerViewBean.getPokerMessage().setShowEstimate(pokerMessage.getShowEstimate());
                pokerViewBean.setIsVoted(Boolean.FALSE);
                Task task = taskFacadeRemote.find(Long.parseLong(pokerMessage.getTaskCode()));
                task.setUserEstimates(new ArrayList<UserEstimate>());
                pokerViewBean.setTaskSelected(task);
                pokerViewBean.getUserEstimate().setTask(task);
                pokerViewBean.getUserEstimate().setPoints(null);
                pokerViewBean.getUserEstimate().setHours(null);
            } else if (pokerMessage.getIsClean()) {
                pokerViewBean.setIsVoted(Boolean.FALSE);
                pokerViewBean.getUserEstimate().setPoints(null);
                pokerViewBean.getUserEstimate().setHours(null);
                pokerViewBean.getPokerMessage().setShowEstimate(Boolean.FALSE);   
                mainLoop:
                for (History history : pokerViewBean.getSprintHistories()) {
                    for (Milestone milestone : history.getMilestones()) {
                        for (Task task : milestone.getTasks()) {
                            if (task.equals(pokerViewBean.getTaskSelected())) {
                                task.setPoints(pokerViewBean.getTaskSelected().getPoints());
                                task.setEstimatedDuration(pokerViewBean.getTaskSelected().getEstimatedDuration());
                                task.setUserEstimates(pokerViewBean.getTaskSelected().getUserEstimates());
                                break mainLoop;
                            }
                        }
                    }
                }
                pokerViewBean.setTaskSelected(null);
            }

        } catch (IOException ex) {
            Logger.getLogger(pokerRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendTaskSelected() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            String sprintCode = pokerViewBean.getSprint().getId().toString();
            String selectedTaskCode = pokerViewBean.getTaskSelected().getId().toString();
            pokerViewBean.getPokerMessage().setTaskCode(selectedTaskCode);
            pokerViewBean.getPokerMessage().setShowEstimate(showEstimate);
            pokerViewBean.getPokerMessage().setIsVote(Boolean.FALSE);
            pokerViewBean.getPokerMessage().setIsTask(Boolean.TRUE);
            pokerViewBean.getPokerMessage().setIsClean(Boolean.FALSE);
            String jsonMessage = mapper.writeValueAsString(pokerViewBean.getPokerMessage());
            eventBus.publish(CHANNEL + sprintCode + "/*", jsonMessage);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(pokerRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendVote() {
        try {
            final ObjectMapper mapper = new ObjectMapper();

            String sprintCode = pokerViewBean.getSprint().getId().toString();
            String selectedTaskCode = pokerViewBean.getTaskSelected().getId().toString();
            pokerViewBean.getPokerMessage().setTaskCode(selectedTaskCode);
            pokerViewBean.getPokerMessage().setShowEstimate(showEstimate);
            pokerViewBean.getPokerMessage().setIsVote(Boolean.TRUE);
            pokerViewBean.getPokerMessage().setIsTask(Boolean.FALSE);
            pokerViewBean.getPokerMessage().setIsClean(Boolean.FALSE);
            pokerViewBean.getPokerMessage().getPokerVote().getSprintVelocity().setPoint(pokerViewBean.getUserEstimate().getPoints());
            pokerViewBean.getPokerMessage().getPokerVote().getSprintVelocity().setHours(pokerViewBean.getUserEstimate().getHours());
            pokerViewBean.getPokerMessage().getPokerVote().setSprintUser(pokerViewBean.getUserEstimate().getSprintUser().getId().toString());
            String jsonMessage = mapper.writeValueAsString(pokerViewBean.getPokerMessage());
            eventBus.publish(CHANNEL + sprintCode + "/*", jsonMessage);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(pokerRequestBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registerVote() {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            String sprintCode = pokerViewBean.getSprint().getId().toString();
            taskFacadeRemote.edit(pokerViewBean.getTaskSelected());
            pokerViewBean.getPokerMessage().setTaskCode(null);
            pokerViewBean.getPokerMessage().setShowEstimate(Boolean.FALSE);
            pokerViewBean.getPokerMessage().setIsVote(Boolean.FALSE);
            pokerViewBean.getPokerMessage().setIsTask(Boolean.FALSE);
            pokerViewBean.getPokerMessage().setIsClean(Boolean.TRUE);
            String jsonMessage = mapper.writeValueAsString(pokerViewBean.getPokerMessage());
            eventBus.publish(CHANNEL + sprintCode + "/*", jsonMessage);
        } catch (UpdateException ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", ex.getMessage()));
        } catch (JsonProcessingException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No se pudo regsitrar el voto"));
        }
    }

    /**
     * Creates a new instance of pokerRequestBean
     */
    public pokerRequestBean() {
    }

}
