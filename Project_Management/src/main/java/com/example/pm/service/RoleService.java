package com.example.pm.service;

import com.example.pm.dto.RoleDto;
import com.example.pm.model.Role;
import com.example.pm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    public List<Role> findByIdIn(List<Integer> listId) {
        return roleRepository.findByIdIn(listId);
    }

    public List<Role> findByIdIn(Integer... listIds) {
        return roleRepository.findByIdIn(listIds);
    }

    public List<RoleDto> findAll() {
        return getRolesDto(roleRepository.findAll());
    }

    public Role getRole(RoleDto roleDto) {
        return Role.builder().id(roleDto.getId()).role(roleDto.getRole()).value(roleDto.getValue()).build();
    }

    public RoleDto getRoleDto(Role role) {
        return RoleDto.builder().id(role.getId()).role(role.getRole()).value(role.getValue()).build();
    }

    public List<Role> getRoles(List<RoleDto> roleDtos) {
        List<Role> roles = new ArrayList<>();
        for (RoleDto roleDto : roleDtos) {
            roles.add(getRole(roleDto));
        }
        return roles;
    }

    public List<RoleDto> getRolesDto(List<Role> roleList) {
        List<RoleDto> roles = new ArrayList<>();
        for (Role role : roleList) {
            roles.add(getRoleDto(role));
        }
        return roles;
    }

    public List<Integer> getRolesIdDto(List<Role> roles) {
        List<Integer> listId = new ArrayList<>();
        for (Role role : roles) {
            listId.add(role.getId());
        }
        return listId;
    }
}
