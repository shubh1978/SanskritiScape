package org.example.service;

import org.example.dto.AuthToken;
import org.example.dto.UserRegistrationDto;
import org.example.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    UserRepository userRepository;

    public AuthToken signup(UserRegistrationDto requestDTO) {
//        if (userRepository.findByEmail(requestDTO.getEmail())) {
//            throw new DataIntegrityViolationException("user with this username already exist. " +
//                    "Please try another username !");
//        }
        User user = User.builder()
                .id(UUID.randomUUID())
                .email(requestDTO.getEmail())
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .password(new BCryptPasswordEncoder().encode(requestDTO.getPassword()))
                .build();

        User savedUser = userRepository.save(user);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return AuthToken.builder()
                .token(authUtil.generateToken(savedUser.getEmail()))
                .authentication(authentication)
                .build();
    }

    private String checkPassword(String password, String username) {
        Optional<User> userData = Optional.ofNullable(userRepository.findByEmail(username));
        if (userData.isPresent()) {
            if (new BCryptPasswordEncoder().matches(password, userData.get().getPassword())) {
                return userData.get().getPassword();
            } else {
                return password;
            }
        } else {
            return password;
        }

    }

    public void changePassword(String username, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(username);
        user.setPassword(newPassword);
        userRepository.save(user);

    }


    public AuthToken login(String username, String password) {

        String encryptedPassword = checkPassword(password, username);

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encryptedPassword)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        User user = userRepository.findByEmail(username);
        return AuthToken.builder()
                .token(authUtil.generateToken(username))
                .authentication(authentication)
                .createdOn(System.currentTimeMillis())
                .build();
    }

}