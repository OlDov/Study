package com.dovgan.spring.AOP;

import com.dovgan.spring.core.loggers.FileEventLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alexdovgan on 1/25/16.
 */
@Aspect
@Component
public class StatisticsAspect {
    public static final Logger LOG = Logger.getLogger( StatisticsAspect.class.getName() );
    private Map<Class<?>, Integer> counter = new HashMap<Class<?>, Integer>();
    public static int MAX_ALLOWED = 2;
    //для всех методов с именем logEvent
    @Pointcut("execution(* *.eventLog(..))")
    private void allLogEventMethods(){
        LOG.info("@@@@@@@@@@@");
    }
    //для всех методов с именем logEvent и
    @Pointcut("within(com.dovgan.spring.core.loggers.ConsoleEventLogger)")
    private void consoleLoggerMethods(){
        LOG.info(";;;;;;;;;;;;;");
    }
    //для вызова метода allLogEventMethods И метода с именем File*Logger
    @Pointcut("allLogEventMethods() && within(*.*FileEventLogger))")
    private void logEventInsiderFileLoggers(){
        LOG.info("poinCut logEventInsiderFileLoggers !!!");
    }
    //advice перед вызовом метода allLogEventMethods
    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint){
        LOG.info("BEFORE :" + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName());
    }
    //advice после выполнения среза с именем allLogEventMethods
    @AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
    public void logAfter(Object retVal){
        LOG.info("Returned value: " + retVal);
    }
    //advice после исключения в методе allLogEventMethods
    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "ex")
    public void logAfterThrow(Throwable ex){
        LOG.warning("allLogEventMethods Thrown: " + ex );
    }
    //advice после выполнения метода allLogEventMethods
    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp){
        Class<?> clazz = jp.getTarget().getClass();
        if(!counter.containsKey(clazz))
            counter.put(clazz,0);
        counter.put(clazz,counter.get(clazz)+1);
        for (Map.Entry<Class<?>, Integer> s : counter.entrySet())
            System.out.println(s.getKey() + "=" + s.getValue());
        System.out.println("________");
    }

    //вокруг выполнения метода ConsoleLoggerMethods
    @Around("consoleLoggerMethods() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt){
        if (counter.get(0) < MAX_ALLOWED)
            try {
                jp.proceed(new Object[] {evt});
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        else
        System.out.println("+++++++++++++"); //((FileEventLogger)evt).logEvent(evt);
    }
}
