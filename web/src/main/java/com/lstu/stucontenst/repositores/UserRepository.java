package com.lstu.stucontenst.repositores;

import com.lstu.stucontenst.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmailAndPassword(String email, String password);
    Optional<UserEntity> findUserByEmail(String email);
}
