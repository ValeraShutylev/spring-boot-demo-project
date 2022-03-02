package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Iterable<User> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE User SET salary = :salary WHERE id = :id")
    void updateSalaryByUserId(String id, long salary);
}
