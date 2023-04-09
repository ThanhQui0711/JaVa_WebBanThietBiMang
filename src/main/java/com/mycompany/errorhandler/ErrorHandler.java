package com.mycompany.errorhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = IllegalStateException.class)
    public void handleForbidden(HttpServletResponse response, IllegalStateException ex) throws IOException {
        ex.printStackTrace();
        response.sendError(403);
    }

    @ExceptionHandler(value = Exception.class)
    public void handleNotFound(HttpServletResponse response, Exception ex) throws IOException {
        ex.printStackTrace();
        response.sendError(404);
    }
}
