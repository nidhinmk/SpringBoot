package com.nidhin.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //pointcut expression
    //@Before("execution(public void add*())")
    //@Before("execution(* add*(com.nidhin.aopdemo.Account, ..))") //Any return type
    @Before("execution(* com.nidhin.aopdemo.dao.*.*(..))") //Return type package.class.method(params)
    public void beforeAddAccountAdvice(){
        System.out.println("\n=====> Executing @Before on addAccount()");
    }

}
