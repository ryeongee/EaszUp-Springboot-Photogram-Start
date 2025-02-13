package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // final fields 를  DI 할 때 쓴다. final 이 걸려있는 모든 Args 에 생성자를 걸어줌.
@Controller //1. IoC 2. file 을 return 하는 컨트롤러
public class AuthController {
//    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

//    public  AuthController(AuthService authService){
//        this.authService = authService;
//    }
    @GetMapping("/auth/signin")
    public String signinForm(){
        return "/auth/signin";
    }
    @GetMapping("/auth/signup")
    public String signupForm(){
        return "/auth/signup";
    }
    //회원가입버튼->/auth/signup->/auth/signin
    //회원가입버튼x
    @PostMapping("/auth/signup") //controller에 responsebody있으면 데이터를 응답함
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){ // key = value (x-www-form-urlencoded)
//        log.info(String.valueOf(signupDto));
        if (bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){ //list
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("=========================================");
                System.out.println(error.getDefaultMessage()) ;
                System.out.println("=========================================");
            }
//            System.out.println(errorMap);
            throw new CustomValidationException("유효성검사 실패함", errorMap); // exception이 낚아챔 but error msg 리턴하고싶음

        } else {
            //User <- SignupDto
            User user = signupDto.toEntitiy();
//            log.info(user.toString());
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            return "/auth/signin";
        }
    }
}
