package com.chepogi.newvtu.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chepogi.newvtu.exceptions.DeviceUserException;
import com.chepogi.newvtu.model.DeviceUser;
import com.chepogi.newvtu.repository.DeviceUserRepository;
import com.chepogi.newvtu.service.DeviceUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeviceUserServiceImpl implements DeviceUserService {

    DeviceUserRepository deviceUserRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<DeviceUser> findAllDeviceUsers() {
        return deviceUserRepository.findAll();
    }

    @Override
    @Transactional
    public String saveDeviceUser(DeviceUser newDeviceUser) throws DeviceUserException {
        Optional<DeviceUser> duplicateDeviceUser = deviceUserRepository.findByLastNameIgnoreCase(newDeviceUser.getLastName());

        if(duplicateDeviceUser.isPresent()){
            throw new DeviceUserException(String.format("there is a type with name %s", newDeviceUser.getLastName()));
        }
        DeviceUser deviceUser = deviceUserRepository.save(newDeviceUser);
        return "operation was successful";
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findDeviceUserById(id);
        deviceUserRepository.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public DeviceUser findDeviceUserById(Long id) throws DeviceUserException {
        Optional<DeviceUser> deviceUser = deviceUserRepository.findById(id);
        return deviceUser.orElseThrow(() -> new DeviceUserException(String.format("We can`t find requested device user")));
    }

    @Override
    @Transactional
    public String updateDeviceUser(DeviceUser deviceUser) {
//        Optional<DeviceUser> duplicateDeviceUser = deviceUserRepository.findByLastNameIgnoreCase(deviceUser.getLastName());
//        if(duplicateDeviceUser.isPresent()){
//            throw new DeviceUserException(String.format("there is a type with name %s", deviceUser.getLastName()));
//        }
        DeviceUser updatedDeviceUser = deviceUserRepository.save(deviceUser);
        if(updatedDeviceUser != null) {
        return "Device user has been succeessfully updated";
        }
        return "Device user hasn't been updated";
    }

}
