package com.holo.dao.my;

import org.springframework.data.jpa.repository.JpaRepository;
import com.holo.domain.my.User;


public interface UserRepo extends JpaRepository<User, Integer> {}