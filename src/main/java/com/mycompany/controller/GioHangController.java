package com.mycompany.controller;

import com.mycompany.dto.Result;
import com.mycompany.entity.HoaDon;
import com.mycompany.entity.SanPhamTrongGioHang;
import com.mycompany.service.SanPhamTrongGioHangService;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/gio-hang")
public class GioHangController {

    private static final String PAGE_URL_GIO_HANG = "page/giohang.jsp";

    @Autowired
    private SanPhamTrongGioHangService sanPhamTrongGioHangService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("gioHang", this.sanPhamTrongGioHangService.findAll());
        return RouterUtils.routeUserLayout(PAGE_URL_GIO_HANG, model);
    }

    @GetMapping("/delete/{id}")
    public String deleteSanPhamTrongGio(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Result<SanPhamTrongGioHang> result = sanPhamTrongGioHangService.deleteSanPhamTrongGioHang(id);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/gio-hang";
    }

    @GetMapping("/congsoluong/{id}")
    public String showFormForUpdatecong(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Result<SanPhamTrongGioHang> result = this.sanPhamTrongGioHangService.congSoLuong(id);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/gio-hang";
    }

    @GetMapping("/trusoluong/{id}")
    public String showFormForUpdatetru(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Result<SanPhamTrongGioHang> result = this.sanPhamTrongGioHangService.truSoLuong(id);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/gio-hang";
    }

    @GetMapping("/checkout")
    public String checkout(HoaDon hoaDon, RedirectAttributes redirectAttributes) {
        Result<?> checkout = this.sanPhamTrongGioHangService.checkout(hoaDon);
        redirectAttributes.addFlashAttribute("result", checkout);
        return "redirect:/hoa-don";
    }
}
