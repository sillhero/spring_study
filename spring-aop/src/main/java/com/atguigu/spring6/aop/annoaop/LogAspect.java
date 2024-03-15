package com.atguigu.spring6.aop.annoaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 切面类
@Aspect // 切面类
@Component // ioc容器
public class LogAspect {

    // 设置切入点和通知类型
    // 切入表达式
    // 通知类型：前置 返回 异常 后置 环绕
    // 前置 @Befor()
    // 返回 @AfterReturning()
    // 异常 @AfterThrowing()
    // 后置 @After()
    // 环绕 @Around

    @Before(value = "execution(public int com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..)) ")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("前置通知，方法名称为" + methodName + ", 参数" + Arrays.toString(args));
    }

    @After(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知，方法名称为" + methodName);
    }

    // 能够得到目标方法的返回值
    @AfterReturning(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知，方法名称为" + methodName + "，返回的结果：" + result);
    }

    // 目标方法出现异常，这个通知会执行
    @AfterThrowing(value = "execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知，方法名称为" + methodName + "异常信息为：" + ex);
    }

    // 环绕
    @Around(value = "pointcut()")
    public void aroundMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        try {
            System.out.println("环绕通知");
        } catch(Throwable throwable) {

        } finally {

        }
        System.out.println("环绕通知，方法名称为" + methodName);

    }

    @Pointcut("execution(* com.atguigu.spring6.aop.annoaop.CalculatorImpl.*(..))")
    public void pointcut() {

    }


}
