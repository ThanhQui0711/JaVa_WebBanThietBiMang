package com.mycompany.controller;

import com.mycompany.dto.Result;
import com.mycompany.entity.KhachHang;
import com.mycompany.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private KhachHangService khachHangService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage() {
        return "registration-form";
    }

    @PostMapping("/showRegistrationForm")
    public String processRegistrationForm(@Validated KhachHang khachHang, BindingResult bindingResult, Model model) {
        Result<KhachHang> result = this.khachHangService.saveKhachHang(khachHang, bindingResult);
        if (result.getError()) {
            model.addAttribute("result", result);
            return "registration-form";
        }
        return "redirect:/showMyLoginPage";
    }
}