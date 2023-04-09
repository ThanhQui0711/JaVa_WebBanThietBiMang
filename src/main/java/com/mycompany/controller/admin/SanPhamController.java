package com.mycompany.controller.admin;

import com.mycompany.dto.Result;
import com.mycompany.dto.SanPhamDTO;
import com.mycompany.dto.pagination.PaginationResponse;
import com.mycompany.entity.DanhMuc;
import com.mycompany.entity.SanPham;
import com.mycompany.service.DanhMucService;
import com.mycompany.service.SanPhamService;
import com.mycompany.utils.PaginationUtils;
import com.mycompany.utils.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;

@Controller("AdminSanPhamController")
@RequestMapping("/admin/products")
public class SanPhamController {

    private static final String PAGE_URL_PRODUCTs = "page/products/products.jsp";

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DanhMucService danhMucService;

    @InitBinder
    public void customBinding(WebDataBinder binder) {
        binder.registerCustomEditor(DanhMuc.class, "danhMuc", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                DanhMuc danhMuc = danhMucService.findById(Long.parseLong(text));
                this.setValue(danhMuc);
            }
        });
    }

    @GetMapping
    public String index(SanPhamDTO sanPhamDTO, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PaginationResponse<SanPham> products = this.sanPhamService.findAll(sanPhamDTO, page);
        PaginationUtils.paginate(model, products);

        model.addAttribute("products", products);
        model.addAttribute("categories", this.danhMucService.findAll());
        return RouterUtils.routeLayout(PAGE_URL_PRODUCTs, model);
    }

    @PostMapping
    public String save(SanPham sanPham, @RequestParam(required = false) MultipartFile hinhAnh, RedirectAttributes redirectAttributes) {
        Result<SanPham> result = this.sanPhamService.saveSanPham(sanPham, hinhAnh);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Result<SanPham> result = this.sanPhamService.deleteSanPham(id);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/admin/products";
    }
}