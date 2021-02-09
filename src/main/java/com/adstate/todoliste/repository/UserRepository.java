package com.adstate.todoliste.repository;

import com.adstate.todoliste.datatype.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUserName(String userName);
}
