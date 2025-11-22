package com.example.BillingSoftware.service.impl;

import com.example.BillingSoftware.entity.userEntity;
import com.example.BillingSoftware.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class appUserDetailsService implements UserDetailsService{

    private final userRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        userEntity existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found for this given E-mail: " + email));
        return new User(existingUser.getEmail(), existingUser.getPassword() ,
                Collections.singleton(new SimpleGrantedAuthority(existingUser.getRole())));
    }
}
