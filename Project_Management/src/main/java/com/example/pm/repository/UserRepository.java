package com.example.pm.repository;

import com.example.pm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndIsDeleted (String email, Boolean isDeleted);

    User findByIdAndIsDeleted(Long id, Boolean isDeleted);

    List<User> findAllByIsDeleted (Boolean isDeleted);

    List<User> findAllByIsDeletedAndIdNotIn (Boolean isDeleted, Long id);
}
