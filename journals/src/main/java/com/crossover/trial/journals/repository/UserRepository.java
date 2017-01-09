package com.crossover.trial.journals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.trial.journals.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginName(String loginName);

    @Transactional(readOnly = true)
    @Query(value = "SELECT p.email FROM user p JOIN subscription n ON p.id = n.user_id AND n.category_id = ?1", nativeQuery = true)
    String[] fetchSubscribersByCategory(Long categoryId);

    @Transactional(readOnly = true)
    @Query(value = "SELECT p.email FROM user p", nativeQuery = true)
    String[] fetchAllUsers();
}
