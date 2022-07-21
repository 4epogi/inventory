package com.chepogi.newvtu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.chepogi.newvtu.model.Department;
import com.chepogi.newvtu.model.DeviceUser;
import com.chepogi.newvtu.service.DepartmentService;
import com.chepogi.newvtu.service.DeviceUserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@RequestMapping("/deviceusers")
@Slf4j
public class DeviceUserController {
    DeviceUserService deviceUserService;
    DepartmentService departmentService;

    @GetMapping("/users")
    public String showDeviceUsersList(Model theModel) {
        List<DeviceUser> deviceUsers = deviceUserService.findAllDeviceUsers();
        theModel.addAttribute("deviceusers", deviceUsers);

        List<Department> departments = departmentService.list();
        theModel.addAttribute("departments", departments);

        if (!theModel.containsAttribute("message")) {
            theModel.addAttribute("message", "");
        }
        DeviceUser deviceUser = new DeviceUser();
        theModel.addAttribute("deviceuser", deviceUser);

        return "device-user/device-users";
    }

    @PostMapping("/save")
    public RedirectView saveNewDeviceType(@ModelAttribute("deviceuser") DeviceUser deviceUser,
            RedirectAttributes redir) {
        log.info(deviceUser.getLastName());
        String resultMessage = deviceUserService.saveDeviceUser(deviceUser);

        RedirectView redirectView = new RedirectView("/deviceusers/users", true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }
    
    @GetMapping("delete")
    public String deleteDeviceUser(@RequestParam("userId") Long id) {
        deviceUserService.deleteById(id);
        return "redirect:/deviceusers/users";
    }
    
    @GetMapping("edit")
    public String editDeviceUser(@RequestParam("userId") Long id, Model theModel) {
        DeviceUser deviceUser = deviceUserService.findDeviceUserById(id);
        theModel.addAttribute("deviceuser", deviceUser);
        
        List<Department> departments = departmentService.list();
        theModel.addAttribute("departments", departments);
        
        return "device-user/edit-deviceuser";
    }
    
    @PostMapping("edit")
    public RedirectView editDeviceUser(@ModelAttribute("edited") DeviceUser editedDeviceUser, RedirectAttributes redir) {

        String resultMessage = deviceUserService.updateDeviceUser(editedDeviceUser);
        RedirectView redirectView = new RedirectView("/deviceusers/users", true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }
}
