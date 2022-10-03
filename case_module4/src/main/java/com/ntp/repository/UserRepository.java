package com.ntp.repository;

import com.ntp.model.User;
import com.ntp.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.ntp.model.dto.UserDTO (u.id,u.fullName, u.password,u.phone, u.username,u.role, u.status)  FROM User u  WHERE u.deleted = false AND u.id = ?1 ")
    Optional<UserDTO> findByUserId(Long id);

    @Query("SELECT NEW com.ntp.model.dto.UserDTO (u.id, u.username) FROM User u WHERE u.username = ?1")
    Optional<UserDTO> findUserDTOByUsername(String username);

    @Query("SELECT NEW com.ntp.model.dto.UserDTO (u.id,u.fullName,u.password, u.phone, u.username,u.role, u.status)  FROM User u  WHERE u.deleted = false ")
    List<UserDTO> findAllUserDTOByDeletedIsFalse();

    @Query("SELECT NEW com.ntp.model.dto.UserDTO (u.id, u.username) FROM User u WHERE u.phone = ?1")
    Optional<UserDTO> findUserDTOByUserPhone(String phone);
}
