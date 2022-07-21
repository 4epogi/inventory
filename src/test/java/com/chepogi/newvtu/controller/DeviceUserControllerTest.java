package com.chepogi.newvtu.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.chepogi.newvtu.service.DepartmentService;
import com.chepogi.newvtu.service.DeviceUserService;

@TestPropertySource("/application.properties")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
//@WebMvcTest(DeviceUserController.class)
@ActiveProfiles("test")
class DeviceUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    DeviceUserService deviceUserService;

    @Mock
    DepartmentService departmentService;

    @Test
    void shouldReturnDeviceUsersView() throws Exception {
//        DeviceUser deviceUser = DeviceUser.builder()
//                .id(1L).firstName("Ivan")
//                .lastName("Ivanov")
//                .department(new Department(1L, "CKO", null))
//                .build();
//        List<DeviceUser> deviceUsers = new ArrayList<>(Arrays.asList(deviceUser));
//
//        Department department = Department.builder()
//                .id(1L).name("CKO")
//                .deviceUsers(deviceUsers)
//                .build();
//        List<Department> departments = new ArrayList<>(Arrays.asList(department));

        Mockito.when(deviceUserService.findAllDeviceUsers())
            .thenReturn(null);
        Mockito.when(departmentService.list())
            .thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/deviceusers/users"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav, "device-user/device-users");
    }
}
