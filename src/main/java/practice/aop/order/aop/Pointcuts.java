package practice.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Pointcuts {
    //practice.aop.order 패치키와 하위 패키지
    @Pointcut("execution(* practice.aop.order..*(..))")
    public void allOrder() {} //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
