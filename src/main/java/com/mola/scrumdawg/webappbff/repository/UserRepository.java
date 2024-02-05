package com.mola.scrumdawg.webappbff.repository;

import com.mola.scrumdawg.webappbff.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
