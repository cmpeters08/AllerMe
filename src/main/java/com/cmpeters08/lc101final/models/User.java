package com.cmpeters08.lc101final.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmp on 7/25/2017.
 */

//@Entity
public class User {

    //@NotNull
    //@Size(min=4, max=20)
    private String username;

    //@Id
   // @GeneratedValue
    private int id;

    //@OneToMany
    //@JoinColumn(name="user_id")
    private List<Trigger> triggers = new ArrayList<>();


    //GETTERS AND SETTERS FOR USER CLASS.
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
