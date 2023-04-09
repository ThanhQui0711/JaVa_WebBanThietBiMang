package com.mycompany.interceptor;

import com.mycompany.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CategoryDataInterceptor implements HandlerInterceptor {

    @Autowired
    private DanhMucService danhMucService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            modelAndView.addObject("categories", this.danhMucService.findAll());
        }
    }
}
