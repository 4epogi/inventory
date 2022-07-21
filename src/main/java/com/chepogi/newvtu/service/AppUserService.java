package com.chepogi.newvtu.service;

import com.chepogi.newvtu.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {
    AppUser findAppUserByUsername(String username);

    void deleteUser(Long id);
    String saveNewUser(AppUser appUser);
    String updateUser(AppUser appUser);
    List<AppUser> list();
    void deleteById(Long id);
    AppUser findAppUserById(Long id);


}
