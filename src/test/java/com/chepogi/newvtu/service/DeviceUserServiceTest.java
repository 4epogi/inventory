package com.chepogi.newvtu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.chepogi.newvtu.exceptions.DeviceUserException;
import com.chepogi.newvtu.model.DeviceUser;
import com.chepogi.newvtu.repository.DeviceUserRepository;

@SpringBootTest
class DeviceUserServiceTest {

    DeviceUser deviceUser;
    @Autowired
    private DeviceUserService deviceUserService;

    @MockBean
    private DeviceUserRepository deviceUserRepository;

    @BeforeEach
    void setUp() {
        deviceUser = DeviceUser.builder().id(1L).firstName("Ivan").lastName("Ivanov").department(null).build();

    }

    @Test
    void shouldThrowDeviceUserExceptionWhenSuchLastNameExists() {
        Mockito.when(deviceUserRepository.findByLastNameIgnoreCase("Ivanov")).thenReturn(Optional.of(deviceUser));
        assertThrows(DeviceUserException.class, () -> {
            deviceUserService.saveDeviceUser(deviceUser);
        });
    }

    @Test
    void shouldSaveUserAndReturnSuccessString() {
        Mockito.when(deviceUserRepository.findByLastNameIgnoreCase("Ivanov")).thenReturn(Optional.empty());
        Mockito.when(deviceUserRepository.save(deviceUser)).thenReturn(deviceUser);

        String expected = "operation was successful";
        String actual = deviceUserService.saveDeviceUser(deviceUser);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnListOfDeviceUser() {
        List<DeviceUser> deviceUserList = new ArrayList<>();
        deviceUserList.add(deviceUser);
        deviceUserList
                .add(DeviceUser.builder().id(2L).firstName("Peter").lastName("Peterson").department(null).build());
        Mockito.when(deviceUserRepository.findAll()).thenReturn(deviceUserList);

        int expectedDeviceUserNumber = 2;
        int actualDeviceUserNumber = deviceUserRepository.findAll().size();

        assertEquals(expectedDeviceUserNumber, actualDeviceUserNumber);
    }

    @Test
    void shouldThrowDeviceUserExceptionWhenNoSuchIdToDelete() {
        Mockito.when(deviceUserRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(DeviceUserException.class, () -> {
            deviceUserService.findDeviceUserById(5L);
        });
    }

    @Test
    void shouldFindDeviceUserById() {
        Mockito.when(deviceUserRepository.findById(1L)).thenReturn(Optional.of(deviceUser));

        String expectedLastName = "Ivanov";
        String actualLastName = deviceUserRepository.findById(1L).get().getLastName();

        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    void shouldReturnSuccessfulUpdateUserMessage() {
        Mockito.when(deviceUserRepository.save(deviceUser)).thenReturn(deviceUser);

        String expectedMessage = "Device user has been succeessfully updated";
        String actualMessage = deviceUserService.updateDeviceUser(deviceUser);

        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    void shouldReturnUnsuccessfulUpdateUserMessage() {
        Mockito.when(deviceUserRepository.save(deviceUser)).thenReturn(null);

        String expectedMessage = "Device user hasn't been updated";
        String actualMessage = deviceUserService.updateDeviceUser(deviceUser);

        assertEquals(expectedMessage, actualMessage);
    }
}
