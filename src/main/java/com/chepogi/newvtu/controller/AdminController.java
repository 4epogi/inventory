package com.chepogi.newvtu.controller;

import com.chepogi.newvtu.model.AppRole;
import com.chepogi.newvtu.model.AppUser;
import com.chepogi.newvtu.model.Department;
import com.chepogi.newvtu.repository.AppUserRepository;
import com.chepogi.newvtu.service.AppRoleService;
import com.chepogi.newvtu.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {
    private AppUserService appUserService;
    private AppRoleService appRoleService;

    @GetMapping("/users")
    public String showAllUsers(Model theModel){
        List<AppUser> users = appUserService.list();
        theModel.addAttribute("users", users);

        if (!theModel.containsAttribute("message")){
            theModel.addAttribute("message", "");
        }
        AppUser appUser = new AppUser();
        theModel.addAttribute("appuser", appUser);

        List<AppRole> roles = appRoleService.getAllRoles();
        theModel.addAttribute("roles", roles);
        return "users";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam("userId") Long id){
        appUserService.deleteById(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/adduser")
    public RedirectView addUser(@ModelAttribute("newuser") AppUser newUser, RedirectAttributes redir){
        log.info(newUser.getUsername());
        String resultMessage = appUserService.saveNewUser(newUser);
        RedirectView redirectView = new RedirectView("/admin/users",true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam("userId") Long id, Model theModel){
        log.info(id.toString());
        AppUser appUser = appUserService.findAppUserById(id);
        theModel.addAttribute("user", appUser);

        log.info(appUser.getRole().getName());
        List<AppRole> roles = appRoleService.getAllRoles();
        theModel.addAttribute("roles", roles);
        return "edit-user";
    }

    @PostMapping("/users/edit")
    public RedirectView editUser(@ModelAttribute("edited") AppUser editedUser, RedirectAttributes redir){
        log.info(editedUser.getUsername());
        log.info(editedUser.getId().toString());
        String resultMessage = appUserService.updateUser(editedUser);
        RedirectView redirectView = new RedirectView("/admin/users",true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }
}
