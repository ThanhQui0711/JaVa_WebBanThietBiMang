package com.mycompany.controller;

import com.mycompany.entity.HoaDon;
import com.mycompany.service.HoaDonService;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    private static final String PAGE_URL_HOA_DON = "page/hoadon.jsp";
    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping
    public String index(Model model) {
        List<HoaDon> hoaDon = this.hoaDonService.findByCurrentUser();
        model.addAttribute("hoaDons", hoaDon);
        return RouterUtils.routeUserLayout(PAGE_URL_HOA_DON, model);
    }
}