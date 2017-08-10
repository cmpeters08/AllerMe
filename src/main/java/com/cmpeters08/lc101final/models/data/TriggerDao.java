package com.cmpeters08.lc101final.models.data;

import com.cmpeters08.lc101final.models.Trigger;
import com.cmpeters08.lc101final.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by cmp on 8/1/2017.
 */
public interface TriggerDao extends CrudRepository<Trigger, Integer> {

    ArrayList<Trigger> findByUser(User user);

}