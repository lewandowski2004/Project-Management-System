package com.example.pm.service;

import com.example.pm.configuration.SecurityConfiguration;
import com.example.pm.dto.ModifyUserDto;
import com.example.pm.dto.UserDto;
import com.example.pm.model.User;
import com.example.pm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public void saveUser(ModifyUserDto modifyUserDto) {
        String pass = securityConfiguration.bCryptPasswordEncoder().encode(modifyUserDto.getPassword());
        User user = User.builder()
                .firstName(modifyUserDto.getFirstName())
                .lastName(modifyUserDto.getLastName())
                .email(modifyUserDto.getEmail())
                .password(pass)
                .roles(roleService.findByIdIn(modifyUserDto.getRoles()))
                .isDeleted(false)
                .build();
        userRepository.save(user);
    }

    public List<UserDto> findAllWithoutPassword() {
        return getEmployeeListWithoutPassword(userRepository.findAllByIsDeleted(false));
    }

    public List<UserDto> getEmployeeListWithoutPassword(List<User> userList) {
        List<UserDto> employeeDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = getEmployeeDtoWithoutPasswordAndAssigned(user);
            employeeDtoList.add(userDto);
        }
        return employeeDtoList;
    }

    public UserDto getEmployeeDtoWithoutPasswordAndAssigned(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(roleService.getRolesDto(user.getRoles()))
                .build();
    }

    public boolean emailIsUnique(ModifyUserDto userDto) {
        User user = userRepository.findByIdAndIsDeleted(userDto.getId(), false);
        if (user != null) {
            if (user.getEmail().equals(userDto.getEmail())) {
                return false;
            }
        }
        return userRepository.findByEmailAndIsDeleted(userDto.getEmail(), false).isPresent();
    }

    public void saveOrUpdateEmployee(ModifyUserDto newUser) {
        if (newUser.getId() == null) {
            saveUser(newUser);
        } else {
            update(newUser);
        }
    }

    public void update(ModifyUserDto userDto) {
        User user = userRepository.findByIdAndIsDeleted(userDto.getId(), false);
        if (user != null) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setRoles(roleService.findByIdIn(userDto.getRoles()));
            user.setEmail(userDto.getEmail());
            if (!userDto.getPassword().isEmpty()) {
                String newPassword = securityConfiguration.bCryptPasswordEncoder().encode(userDto.getPassword());
                user.setPassword(newPassword);
            }
        }
    }

    public UserDto finById(Long id) {
        return getUserDto(userRepository.getOne(id));
    }

    public UserDto getUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roleService.getRolesDto(user.getRoles()))
                .build();
    }

    public void deleteUser(Long id) {
        if (id != null) {
            User user = userRepository.findByIdAndIsDeleted(id, false);
            if (user != null) {
                user.setIsDeleted(true);
            }
        }
    }

    public ModifyUserDto findModifyUserById(Long id) {
        if (id != null) {
            User user = userRepository.findByIdAndIsDeleted(id, false);
            if (user != null) {
                return getModifyUserDto(user);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private ModifyUserDto getModifyUserDto(User user) {
        return ModifyUserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roleService.getRolesIdDto(user.getRoles()))
                .build();
    }
    public List<UserDto> findAllWithoutPasswordAndYourself(Long id) {
        return getEmployeeListWithoutPassword(userRepository.findAllByIsDeletedAndIdNotIn(false, id));
    }
}
