package com.nidhin.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //@Before("execution(public void add*())")
    //@Before("execution(* add*(com.nidhin.aopdemo.Account, ..))") //Any return type
    @Before("com.nidhin.aopdemo.aspect.AopExpressinsUtil.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=====> Executing @Before on addAccount()");
    }


}
