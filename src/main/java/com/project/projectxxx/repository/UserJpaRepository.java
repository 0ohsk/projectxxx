package com.project.projectxxx.repository;

import com.project.projectxxx.domain.User;
import com.project.projectxxx.dto.UserDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserJpaRepository {
    private final EntityManager em;

    public Long save(User user){
        em.persist(user);
        return user.getId();
    }
}
