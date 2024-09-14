package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.util.StringUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

//import org.example.dto.LoginRequestDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

//import javax.validation.Valid;
@Slf4j
@Controller
public class UserController {

//    private static final Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup( @RequestBody UserRegistrationDto requestDTO) {
        try {
            AuthToken signup = authService.signup(requestDTO);
            return ResponseEntity.ok(new BaseDataResponse(StringUtil.SUCCESS, signup));
        } catch (DataIntegrityViolationException e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(new BaseResponse(StringUtil.FAILURE, "Bad Request"));
        } catch (Exception e) {
            log.error("error message : {}", e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().body(new BaseResponse(StringUtil.FAILURE, e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO requestDTO) {
        try {
            AuthToken login = authService.login(requestDTO.getEmail(), requestDTO.getPassword());
            return ResponseEntity.ok(new BaseDataResponse(StringUtil.SUCCESS, login));
        } catch (DataIntegrityViolationException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse(StringUtil.FAILURE, "Bad Request"));
        } catch (Exception e) {
            log.error("Error message: {}", e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().body(new BaseResponse(StringUtil.FAILURE, e.getMessage()));
        }
    }


    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        authService.changePassword(changePasswordRequest.getEmail(),
                changePasswordRequest.getCurrentPassword(),
                changePasswordRequest.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }
}