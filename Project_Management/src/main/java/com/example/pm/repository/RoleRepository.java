package com.example.pm.repository;

import com.example.pm.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findById(Integer id);

    List<Role> findByIdIn(List<Integer> id);

    List<Role> findByIdIn(Integer ...ids);
}
