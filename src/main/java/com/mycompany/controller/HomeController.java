package com.mycompany.controller;

import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.SanPham;
import com.mycompany.service.SanPhamService;
import com.mycompany.utils.PaginationUtils;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private static final String PAGE_URL_HOME = "page/home.jsp";
    private static final String PAGE_URL_LIEN_HE = "page/lienhe.jsp";
    private static final String PAGE_URL_GIOI_THIEU = "page/gioithieu.jsp";

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/")
    public String home(SanPhamDTO sanPhamDTO, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PaginationResponse<SanPham> products = this.sanPhamService.findAll(sanPhamDTO, page);
        model.addAttribute("products", products);
        PaginationUtils.paginate(model, products);
        return RouterUtils.routeUserLayout(PAGE_URL_HOME, model);
    }

    @GetMapping("/lien-he")
    public String lienHe(Model model) {
        return RouterUtils.routeUserLayout(PAGE_URL_LIEN_HE, model);
    }

    @GetMapping("/gioi-thieu")
    public String gioiThieu(Model model) {
        return RouterUtils.routeUserLayout(PAGE_URL_GIOI_THIEU, model);
    }
}