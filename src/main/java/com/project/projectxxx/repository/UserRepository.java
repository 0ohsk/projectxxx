package com.project.projectxxx.repository;

import com.project.projectxxx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);
    Optional<User> findByName(String name);


}
