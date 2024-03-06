package com.c1646njava.tuvivienda.repositories;

import com.c1646njava.tuvivienda.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.name = :currentUserName")
    User findByName(String currentUserName);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.country = :country WHERE u.id = :userId")
    void updateUserDetails(Long userId, String name, String email, String country);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :userId")
    void updateUserPassword(Long userId, String password);

}
