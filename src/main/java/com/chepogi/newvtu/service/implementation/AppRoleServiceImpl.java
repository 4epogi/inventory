package com.chepogi.newvtu.service.implementation;

import com.chepogi.newvtu.model.AppRole;
import com.chepogi.newvtu.repository.AppRoleRepository;
import com.chepogi.newvtu.service.AppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppRoleServiceImpl implements AppRoleService {
    private AppRoleRepository appRoleRepository;

    @Override
    public List<AppRole> getAllRoles() {
        return appRoleRepository.findAll();
    }
}
