package com.cmpeters08.lc101final.models;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by cmp on 7/25/2017.
 */

@Entity
@Table(name="triggers")
public class Trigger{

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;



    private String knownTriggers;

    //things suspected to have caused a reaction. an ingredient common in one or more user inputs.
   // private ArrayList<String> knownTriggers;

    //things users have said they know they are not triggered by, ie "Aqua(water)"
  //  private ArrayList<String> triggerExceptions;

    private  static ArrayList<String> triggerResults;

    private String maybeTrigger;



    // GETTERS AND SETTERS FOR THE TRIGGER CLASS.

//    public ArrayList<String> getKnownTriggers() {
//        return knownTriggers;
//    }
//
//    public void setKnownTriggers(ArrayList<String> knownTriggers) {
//        this.knownTriggers = knownTriggers;
//    }

//    public ArrayList<String> getTriggerExceptions() {
//        return triggerExceptions;
//    }
//
//    public void setTriggerExceptions(ArrayList<String> triggerExceptions) {
//        this.triggerExceptions = triggerExceptions;
//    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  static ArrayList<String> getTriggerResults() {
        return triggerResults;
    }

    public void setTriggerResults(ArrayList<String> triggerResults) {
        this.triggerResults = triggerResults;
    }

    public String getKnownTriggers() {
        return knownTriggers;
    }

    public void setKnownTriggers(String knownTriggers) {
        this.knownTriggers = knownTriggers;
    }

    public String getMaybeTrigger() {
        return maybeTrigger;
    }

    public void setMaybeTrigger(String maybeTrigger) {
        this.maybeTrigger = maybeTrigger;
    }
}
