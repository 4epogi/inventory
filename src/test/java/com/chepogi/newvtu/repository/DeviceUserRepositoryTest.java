package com.chepogi.newvtu.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.chepogi.newvtu.model.Department;
import com.chepogi.newvtu.model.DeviceUser;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeviceUserRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DeviceUserRepository deviceUserRepository;

    @BeforeAll
    void setUp() {
        Department department1 = Department.builder().id(1L).name("CKO").build();
        Department department2 = Department.builder().id(2L).name("ОТК").build();
        departmentRepository.save(department1);
        departmentRepository.save(department2);

        DeviceUser deviceUser1 = DeviceUser.builder().id(1L).firstName("Ivan").lastName("Ivanov")
                .department(department1).build();

        DeviceUser deviceUser2 = DeviceUser.builder().id(2L).firstName("Peter").lastName("Petrov")
                .department(department2).build();

        deviceUserRepository.save(deviceUser1);
        deviceUserRepository.save(deviceUser2);
    }
    @Test
    void shouldReturnListOfTwoDeviceUsers() {
        List<DeviceUser> deviceUsers = deviceUserRepository.findAll();
        int expectedListSize = 2;
        int actualListSize = deviceUsers.size();
        assertEquals(expectedListSize, actualListSize);
    }
    
    @Test
    void shouldFindDeviceUserById() {
        DeviceUser deviceUser = deviceUserRepository.findById(1L).get();
        String expectedName = "Ivanov";
        String actualName = deviceUser.getLastName();
        assertEquals(expectedName, actualName);
    }
    
    @Test
    void shouldUpdateDeviceUser() {
        DeviceUser deviceUser = deviceUserRepository.findById(1L).get();
        deviceUser.setLastName("Kozlov");
        deviceUserRepository.save(deviceUser);

        String expectedName = deviceUser.getLastName();
        String actualName = deviceUserRepository.findById(1L).get().getLastName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void shouldFindDeviceUserByLastName() {
        DeviceUser deviceUser = deviceUserRepository.findByLastNameIgnoreCase("ivanOv").get();
        String expectedName = "Ivanov";
        String actualName = deviceUser.getLastName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void shouldDeleteDeviceUser() {
        List<DeviceUser> deviceUsersBefore = deviceUserRepository.findAll();
        int sizeBeforeDeleting = deviceUsersBefore.size();

        deviceUserRepository.delete(deviceUsersBefore.get(0));

        List<DeviceUser> deviceUsersAfter = deviceUserRepository.findAll();
        int sizeAfterDeleting = deviceUsersAfter.size();

        assertThat(sizeBeforeDeleting).isGreaterThan(sizeAfterDeleting);
    }
}
