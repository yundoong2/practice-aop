package practice.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(1)
    public static class LogAspect {
        @Around("practice.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature()); //join point signature
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(2)
    public static class TxAspect {
        @Around("practice.aop.order.aop.Pointcuts.orderAndService()")
        //practice.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                //@Before
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                //@AfterReturning
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                //@AfterThrowing
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally  {
                //@After
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
