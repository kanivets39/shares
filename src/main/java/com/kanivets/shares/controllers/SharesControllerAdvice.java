package com.kanivets.shares.controllers;

import com.kanivets.shares.exeptions.NoEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SharesControllerAdvice {


    @ResponseBody
    @ExceptionHandler(NoEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noShareExceptionHandler(NoEntityException e) { return e.getMessage(); }

}
