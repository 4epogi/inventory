package com.chepogi.newvtu.service;

import java.util.List;

import com.chepogi.newvtu.model.DeviceUser;

public interface DeviceUserService {

    List<DeviceUser> findAllDeviceUsers();

    String saveDeviceUser(DeviceUser newDeviceUser);

    void deleteById(Long id);

    DeviceUser findDeviceUserById(Long id);

    String updateDeviceUser(DeviceUser deviceUser);
}
