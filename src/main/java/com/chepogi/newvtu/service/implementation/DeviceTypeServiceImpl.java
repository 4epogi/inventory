package com.chepogi.newvtu.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chepogi.newvtu.exceptions.DeviceTypeException;
import com.chepogi.newvtu.model.DeviceType;
import com.chepogi.newvtu.repository.DeviceTypeRepository;
import com.chepogi.newvtu.service.DeviceTypeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final DeviceTypeRepository deviceTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DeviceType> getAllDeviceType() {
        // TODO Auto-generated method stub
        return deviceTypeRepository.findAll();
    }

    @Override
    @Transactional
    public String saveDeviceType(DeviceType newDeviceType) throws DeviceTypeException {
        String operationResult;
        Optional<DeviceType> duplicateDepartment = deviceTypeRepository.findByNameIgnoreCase(newDeviceType.getName());

        if(duplicateDepartment.isPresent()){
            throw new DeviceTypeException(String.format("there is a type with name %s", newDeviceType.getName())); 
        }
        DeviceType deviceType = deviceTypeRepository.save(newDeviceType);
        return "operation was successful";
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findDeviceTypeById(id);
        deviceTypeRepository.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public DeviceType findDeviceTypeById(Long id) throws DeviceTypeException {
        Optional<DeviceType> deviceType = deviceTypeRepository.findById(id);
        return deviceType.orElseThrow(() -> new DeviceTypeException(String.format("We can`t find requested device type")));
    }

    @Override
    @Transactional
    public String updateDeviceType(DeviceType deviceType) throws DeviceTypeException {
        Optional<DeviceType> duplicateDeviceType = deviceTypeRepository.findByNameIgnoreCase(deviceType.getName());
        if(duplicateDeviceType.isPresent()){
            throw new DeviceTypeException(String.format("there is a type with name %s", deviceType.getName()));
        }
        DeviceType updatedDeviceType = deviceTypeRepository.save(deviceType);
        if(updatedDeviceType != null) {
        return "Device type has been succeessfully updated";
        }
        return "Device type hasn't been updated";
    }

}
