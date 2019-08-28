package com.threeAier.app.framework;

import com.alibaba.fastjson.JSONObject;
import com.threeAier.app.common.ThreeAierRuntimeException;
import com.threeAier.app.common.base.AppBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@ControllerAdvice
@RestControllerAdvice
public class RuleExceptionHandler extends AppBaseController {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object defaultExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        log.warn("服务端异常", e);
        log.warn("RequestURI:[{}],QueryString:[{}],",
                request.getRequestURI(), request.getQueryString(), request.getHeader("Authorization"));

        JSONObject object = new JSONObject();
        object.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        object.put("message", "服务端异常");

        return object.toString();
    }

    @ExceptionHandler(value = ThreeAierRuntimeException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Object RuleRuntimeExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        log.warn("服务端异常", e);
        log.warn("RequestURI:[{}],QueryString:[{}],",
                request.getRequestURI(), request.getQueryString(), request.getHeader("Authorization"));

        JSONObject object = new JSONObject();
        object.put("code", HttpStatus.EXPECTATION_FAILED.value());
        object.put("message", e.getMessage());


        return object.toString();
    }








}
