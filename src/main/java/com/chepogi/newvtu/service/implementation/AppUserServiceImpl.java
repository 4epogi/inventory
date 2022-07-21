package com.chepogi.newvtu.service.implementation;

import com.chepogi.newvtu.dto.UserDetailsFactory;
import com.chepogi.newvtu.exceptions.BadRequestException;
import com.chepogi.newvtu.model.AppUser;
import com.chepogi.newvtu.model.Department;
import com.chepogi.newvtu.repository.AppUserRepository;
import com.chepogi.newvtu.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser findAppUserByUsername(String username) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public String saveNewUser(AppUser newAppUser) {
        String operationResult;
        log.info(newAppUser.getUsername());
        Optional<AppUser> duplicateAppUser = appUserRepository.findByUsernameIgnoreCase(newAppUser.getUsername());

        if(duplicateAppUser.isPresent()){
            return "We already have user with such username";
        }
        newAppUser.setPassword(bCryptPasswordEncoder.encode(newAppUser.getPassword()));
        log.info(newAppUser.getPassword());
        AppUser appUser = appUserRepository.save(newAppUser);
        return "user was successfully saved";
    }

    @Override
    public String updateUser(AppUser updatedAppUser) {
        updatedAppUser.setPassword(bCryptPasswordEncoder.encode(updatedAppUser.getPassword()));
        log.info(updatedAppUser.getPassword());
        AppUser appUser = appUserRepository.save(updatedAppUser);
        return "user was successfully changed";
    }

    @Override
    public List<AppUser> list() {
        return appUserRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUser findAppUserById(Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        return appUser.orElseThrow(() -> new BadRequestException("UserNotFound"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with login '%s' wasnt found", username)));
        return UserDetailsFactory.AppUserToUserDetails(user);
    }

}
