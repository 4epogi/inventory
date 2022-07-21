package com.chepogi.newvtu.service;

import java.util.List;

import com.chepogi.newvtu.model.DeviceType;

public interface DeviceTypeService {
    List<DeviceType> getAllDeviceType();

    String saveDeviceType(DeviceType newDeviceType);

    void deleteById(Long id);

    DeviceType findDeviceTypeById(Long id);

    String updateDeviceType(DeviceType deviceType);
}
