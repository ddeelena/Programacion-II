package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.model.Candidato;
import edu.co.sistemagestionempleos.model.User;
import edu.co.sistemagestionempleos.repository.UserRepository;
import edu.co.sistemagestionempleos.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }


    public Integer getUserIdByUsername(String username) {
        return userRepository.findIdByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    public User registerUser(String username, String password, String role) {
        System.out.println(username);
        User user = new User();
        System.out.println(user.getRole());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
       //String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role.toUpperCase();
        //user.setRole(Set.of(formattedRole));
        user.setRole("ROLE_" + role.toUpperCase());
        return userRepository.save(user);
    }

}
