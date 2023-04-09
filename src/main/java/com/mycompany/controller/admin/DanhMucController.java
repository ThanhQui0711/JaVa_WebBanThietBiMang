package com.mycompany.controller.admin;

import com.mycompany.dto.Result;
import com.mycompany.dto.pagination.PaginationRequest;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.DanhMuc;
import com.mycompany.service.DanhMucService;
import com.mycompany.utils.PaginationUtils;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("AdminDanhMucController")
@RequestMapping("/admin/categories")
public class DanhMucController {

    private static final String PAGE_URL_CATEGORIES = "page/categories/categories.jsp";

    @Autowired
    private DanhMucService danhMucService;

    @GetMapping
    public String index(Model model, PaginationRequest<String> paginationRequest) {
        PaginationResponse<DanhMuc> categories = this.danhMucService.findAll(paginationRequest);
        PaginationUtils.paginate(model, categories);
        model.addAttribute("categories", categories);
        return RouterUtils.routeLayout(PAGE_URL_CATEGORIES, model);
    }

    @PostMapping
    public String save(DanhMuc danhMuc, RedirectAttributes redirectAttributes) {
        Result<DanhMuc> save = this.danhMucService.save(danhMuc);
        redirectAttributes.addFlashAttribute("result", save);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Result<DanhMuc> delete = this.danhMucService.delete(id);
        redirectAttributes.addFlashAttribute("result", delete);
        return "redirect:/admin/categories";
    }
}
