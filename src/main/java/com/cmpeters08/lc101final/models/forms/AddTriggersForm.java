package com.cmpeters08.lc101final.models.forms;

import com.cmpeters08.lc101final.models.Trigger;
import com.cmpeters08.lc101final.models.User;

import javax.validation.constraints.NotNull;

/**
 * Created by cmp on 8/1/2017.
 */
public class AddTriggersForm {

    @NotNull
    private int userId;

    @NotNull
    private int triggerId;

    private Iterable<Trigger> triggers;

    private User user;

    public AddTriggersForm(User user, Iterable<Trigger> triggers){
        this.user = user;
        this.triggers = triggers;
    }

    //GETTERS AND SETTERS

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(int triggerId) {
        this.triggerId = triggerId;
    }

    public Iterable<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(Iterable<Trigger> triggers) {
        this.triggers = triggers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
