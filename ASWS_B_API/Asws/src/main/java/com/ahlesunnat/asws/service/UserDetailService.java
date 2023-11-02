package com.ahlesunnat.asws.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
   UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
