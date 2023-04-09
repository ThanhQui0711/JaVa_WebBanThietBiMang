package com.mycompany.controller.admin;

import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.HoaDon;
import com.mycompany.service.HoaDonService;
import com.mycompany.utils.PaginationUtils;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("AdminHoaDonController")
@RequestMapping("/admin/receipts")
public class HoaDonController {

    private static final String PAGE_URL_RECEIPTs = "page/receipts/receipts.jsp";

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping
    public String index(@RequestParam(required = false) String username, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PaginationResponse<HoaDon> hoaDons = this.hoaDonService.findAll(username, page);
        PaginationUtils.paginate(model, hoaDons);
        model.addAttribute("hoaDons", hoaDons);
        return RouterUtils.routeLayout(PAGE_URL_RECEIPTs, model);
    }
}
