package com.chepogi.newvtu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.chepogi.newvtu.exceptions.DeviceTypeException;
import com.chepogi.newvtu.model.DeviceType;
import com.chepogi.newvtu.service.DeviceTypeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("devicetypes")
public class DeviceTypeController {
    private final DeviceTypeService deviceTypeService;

    @GetMapping("/types")
    public String showDeviceTypesList(Model theModel) {
        List<DeviceType> deviceTypeList = deviceTypeService.getAllDeviceType();
        theModel.addAttribute("devicetypes", deviceTypeList);

        if (!theModel.containsAttribute("message")) {
            theModel.addAttribute("message", "");
        }
        DeviceType deviceType = new DeviceType();
        theModel.addAttribute("devicetype", deviceType);
        return "device-types";
    }

    @PostMapping("save")
    public RedirectView saveNewDeviceType(@ModelAttribute("devicetype") DeviceType deviceType,
            RedirectAttributes redir) {
        String resultMessage = deviceTypeService.saveDeviceType(deviceType);

        RedirectView redirectView = new RedirectView("/devicetypes/types", true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }

    @GetMapping("delete")
    public String deleteDeviceType(@RequestParam("typeId") Long id) {
        deviceTypeService.deleteById(id);
        return "redirect:/devicetypes/types";
    }

    @GetMapping("edit")
    public String editDeviceType(@RequestParam("typeId") Long id, Model theModel) {
        DeviceType deviceType = deviceTypeService.findDeviceTypeById(id);
        theModel.addAttribute("devicetype", deviceType);
        return "types/edit-type";
    }

    @PostMapping("edit")
    public RedirectView editDeviceType(@ModelAttribute("edited") DeviceType editedDeviceType, RedirectAttributes redir) {

        String resultMessage = deviceTypeService.updateDeviceType(editedDeviceType);
        RedirectView redirectView = new RedirectView("/devicetypes/types", true);
        redir.addFlashAttribute("message", resultMessage);
        return redirectView;
    }
    
//    @ExceptionHandler(DeviceTypeException.class)
//    public ModelAndView handleDeviceTypeNotFound(Exception ex) {
//        ModelAndView modelAndView = new ModelAndView("error/typenotfound");
//        modelAndView.addObject("message", ex.getLocalizedMessage());
//
//        return modelAndView;
//    }
}
