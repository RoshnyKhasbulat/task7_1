package com.example.task7_1.repositories;

import com.example.task7_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findUserByLogin(String userName);
}
