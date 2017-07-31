package com.cmpeters08.lc101final.models.data;

import com.cmpeters08.lc101final.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LaunchCode implemented by cmp on 7/31/2017.
 */

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
