package com.mycompany.controller.admin;

import com.mycompany.dto.Result;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.KhachHang;
import com.mycompany.service.KhachHangService;
import com.mycompany.utils.PaginationUtils;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.Path;

@Controller("AdminKhachHangController")
@RequestMapping("/admin/users")
public class KhachHangController {

    private static final String PAGE_URL_USERS = "page/users/users.jsp";

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping
    public String index(KhachHang khachHang, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PaginationResponse<KhachHang> users = this.khachHangService.findAll(khachHang, page);
        PaginationUtils.paginate(model, users);

        model.addAttribute("users", users);
        return RouterUtils.routeLayout(PAGE_URL_USERS, model);
    }

    @GetMapping("/lock/{username}")
    public String lockUser(@PathVariable String username, Model model) {
        Result<KhachHang> result = this.khachHangService.lockKhachHang(username);
        model.addAttribute("result", result);
        return "redirect:/admin/users";
    }

    @GetMapping("/unlock/{username}")
    public String unlockUser(@PathVariable String username, Model model) {
        Result<KhachHang> result = this.khachHangService.unlockKhachHang(username);
        model.addAttribute("result", result);
        return "redirect:/admin/users";
    }
}
