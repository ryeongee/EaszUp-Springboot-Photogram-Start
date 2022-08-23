package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
@RestController// 응답
@ControllerAdvice //  exception 낚아챔
public class ControllerExceptionHandler {

//    @ExceptionHandler(CustomValidationException.class)
//    public Map<String, String> validationException(CustomValidationException e){
//        return e.getErrorMap();
////        return e.getMessage();
//    }
    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto validationCMRespDtoException(CustomValidationException e){
        return new CMRespDto(e.getMessage(), e.getErrorMap());
    }
}
