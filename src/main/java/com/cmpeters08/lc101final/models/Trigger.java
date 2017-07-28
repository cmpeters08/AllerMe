package com.cmpeters08.lc101final.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

/**
 * Created by cmp on 7/25/2017.
 */

//@Entity
public class Trigger {

    //@Id
    //@GeneratedValue
    private int id;

  //  @ManyToOne
    private User user;


    //things suspected to have caused a reaction. an ingredient common in one or more user inputs.
    private ArrayList<String> knownTriggers;

    //things users have said they know they are not triggered by, ie "Aqua(water)"
    private ArrayList<String> triggerExceptions;

    private  static ArrayList<String> triggerResults;



    // GETTERS AND SETTERS FOR THE TRIGGER CLASS.
    public ArrayList<String> getKnownTriggers() {
        return knownTriggers;
    }

    public void setKnownTriggers(ArrayList<String> knownTriggers) {
        this.knownTriggers = knownTriggers;
    }

    public ArrayList<String> getTriggerExceptions() {
        return triggerExceptions;
    }

    public void setTriggerExceptions(ArrayList<String> triggerExceptions) {
        this.triggerExceptions = triggerExceptions;
    }

    public  static ArrayList<String> getTriggerResults() {
        return triggerResults;
    }

    public void setTriggerResults(ArrayList<String> triggerResults) {
        this.triggerResults = triggerResults;
    }
}
