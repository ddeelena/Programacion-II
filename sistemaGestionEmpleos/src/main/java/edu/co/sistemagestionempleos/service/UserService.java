package edu.co.sistemagestionempleos.service;

import edu.co.sistemagestionempleos.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(String username, String password, String role);
    UserDetails loadUserByUsername(String username);
    Integer getUserIdByUsername(String username);
}
