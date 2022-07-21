package com.chepogi.newvtu.controller;

import com.chepogi.newvtu.exceptions.DepartmentException;
import com.chepogi.newvtu.exceptions.DeviceTypeException;
import com.chepogi.newvtu.model.AppRole;
import com.chepogi.newvtu.model.AppUser;
import com.chepogi.newvtu.model.Department;
import com.chepogi.newvtu.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
@Slf4j
public class HomeController {
    private DepartmentService departmentService;

    @GetMapping("/")
    public String showHomePage(){
        return "home/index";
    }

    @GetMapping("/departments")
    public String showDepartments(Model theModel){
        List<Department> departmentList = departmentService.list();
        theModel.addAttribute("departments", departmentList);

        if (!theModel.containsAttribute("message")){
            theModel.addAttribute("message", "");
        }
        Department department = new Department();
        theModel.addAttribute("department", department);
        return "department";
    }

    @PostMapping("departments/depsave")
    public RedirectView saveNewDepartment(@ModelAttribute("department") Department department, RedirectAttributes redir){
        String resultMessage = departmentService.saveDepartment(department);

        RedirectView redirectView = new RedirectView("/departments",true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }

    @GetMapping("departments/delete")
    public String deleteDepartment(@RequestParam("depId") Long id) {
        departmentService.deleteById(id);
        return "redirect:/departments";
    }

    @GetMapping("departments/edit")
    public String editDepartment(@RequestParam("depId") Long id, Model theModel) throws DepartmentException {
        Department department = departmentService.findDepartmentById(id);
        theModel.addAttribute("department", department);
        return "edit-department";
    }

    @PostMapping("/departments/edit")
    public RedirectView editSaveUser(@ModelAttribute("edited") Department editedDepartment, RedirectAttributes redir){

        String resultMessage = departmentService.updateDepartment(editedDepartment);
        RedirectView redirectView = new RedirectView("/departments",true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }

//
//    @GetMapping("login")
//    public String showLoginPage(){
//        return "login";
//    }
//
//    @GetMapping(value="/logout")
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
//    }
}
