package com.mycompany.controller;

import com.mycompany.dto.Result;
import com.mycompany.entity.SanPham;
import com.mycompany.entity.SanPhamTrongGioHang;
import com.mycompany.service.SanPhamService;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    private static final String PAGE_URL_SAN_PHAM = "page/chitietsanpham.jsp";

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/{masp}")
    public String chiTietSanPham(@PathVariable Integer masp, Model model) {
        SanPham sanPham = this.sanPhamService.getSanPham(masp);
        model.addAttribute("sanPham", sanPham);
        return RouterUtils.routeUserLayout(PAGE_URL_SAN_PHAM, model);
    }

    @PostMapping("/{masp}")
    public String addToCard(@PathVariable Integer masp, @RequestParam(required = false) Integer soLuong, RedirectAttributes redirectAttributes) {
        Result<SanPhamTrongGioHang> result = this.sanPhamService.addToCard(masp, soLuong);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/san-pham/" + masp;
    }
}