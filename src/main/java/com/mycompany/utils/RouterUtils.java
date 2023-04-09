package com.mycompany.utils;

import org.springframework.ui.Model;

public class RouterUtils {

    public static String routeLayout(String pageContentUrl, Model model) {
        model.addAttribute("pageUrl", pageContentUrl);
        return "admin/layout";
    }

    public static String routeUserLayout(String pageContentUrl, Model model) {
        model.addAttribute("pageUrl", pageContentUrl);
        return "user/layout";
    }
}
