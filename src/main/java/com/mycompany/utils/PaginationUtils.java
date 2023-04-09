package com.mycompany.utils;

import com.mycompany.dto.pagination.PaginationResponse;
import org.springframework.ui.Model;

import java.util.stream.IntStream;

public class PaginationUtils {

    public static void paginate(Model model, PaginationResponse paginationResponse) {
        Long total = paginationResponse.getTotal();
        Integer pageSize = paginationResponse.getPageSize();
        int numberPage = Double.valueOf(Math.ceil(total.doubleValue() / pageSize.doubleValue())).intValue();
        int[] pages = IntStream.rangeClosed(1, numberPage).toArray();
        model.addAttribute("pages", pages);
    }
}
