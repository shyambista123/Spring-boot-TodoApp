package com.shyam.todo.repo;

import com.shyam.todo.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDtls, Integer> {
    public boolean existsByEmail(String email);
}
