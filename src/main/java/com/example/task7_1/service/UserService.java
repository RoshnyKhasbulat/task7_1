package com.example.task7_1.service;



import com.example.task7_1.exeptions.NotFoundException;
import com.example.task7_1.model.Role;
import com.example.task7_1.model.User;
import com.example.task7_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Transactional
    public User addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setId(1L);
        userRole.setId(2L);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        if (user.isAdminFlag()) {
            roles.add(adminRole);
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

