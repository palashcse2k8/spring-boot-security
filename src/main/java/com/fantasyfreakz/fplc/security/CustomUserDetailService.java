package com.fantasyfreakz.fplc.security;

import com.fantasyfreakz.fplc.domain.entites.UserCredential;
import com.fantasyfreakz.fplc.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCredential userCredential = userCredentialRepository.findByUsername(username);

        if (userCredential == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(userCredential);
    }
}
