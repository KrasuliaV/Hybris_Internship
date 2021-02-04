package com.ciklum.Hybris_Internship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception exception) {
        return getModelAndView(request, HttpStatus.INTERNAL_SERVER_ERROR, exception);

    }

    private ModelAndView getModelAndView(HttpServletRequest request, HttpStatus status, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("сode", status.value() + " " + status.getClass());
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }


}
