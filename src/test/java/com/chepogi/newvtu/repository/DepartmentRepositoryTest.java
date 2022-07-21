package com.chepogi.newvtu.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.chepogi.newvtu.model.Department;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

//    @Autowired
//    private TestEntityManager testEntityManager;

    @BeforeAll
    void setUp() {
        Department department1 = Department.builder().id(1L).name("CKO").build();
        Department department2 = Department.builder().id(2L).name("ОТК").build();
        departmentRepository.save(department1);
        departmentRepository.save(department2);
//
//        testEntityManager.persist(department);

    }

    @Test
    void shouldFindDeviceUserById() {
        Department department = departmentRepository.findById(1L).get();
        String expectedName = "CKO";
        String actualName = department.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void shouldReturnListOfTwoDepartments() {
        List<Department> departments = departmentRepository.findAll();
        int expectedListSize = 2;
        int actualListSize = departments.size();
        assertEquals(expectedListSize, actualListSize);
    }

}
