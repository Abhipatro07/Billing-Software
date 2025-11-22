package com.example.BillingSoftware.controller;

import com.example.BillingSoftware.io.authRequest;
import com.example.BillingSoftware.io.authResponse;
import com.example.BillingSoftware.service.impl.appUserDetailsService;
import com.example.BillingSoftware.service.userService;
import com.example.BillingSoftware.util.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class authController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final appUserDetailsService appUserDetailsService;
    private final jwtUtil jwtUtil;
    private final userService userService;

    @PostMapping("login")
    public authResponse login(@RequestBody authRequest request) throws Exception{
        authenticate(request.getEmail() , request.getPassword());
        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        String role = userService.getUserRole(request.getEmail());
        return new authResponse(request.getEmail() , role , jwtToken);
    }

    private void authenticate(String email, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email , password));
        }
        catch(DisabledException e){
            throw new Exception("User disabled");
        }
        catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "Email or Password is incorrect");
        }
    }


    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String , String> request){
        return passwordEncoder.encode(request.get("password"));
    }

}
