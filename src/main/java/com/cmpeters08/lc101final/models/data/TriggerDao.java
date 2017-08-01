package com.cmpeters08.lc101final.models.data;

import com.cmpeters08.lc101final.models.Trigger;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cmp on 8/1/2017.
 */
public interface TriggerDao extends CrudRepository<Trigger, Integer> {
}
