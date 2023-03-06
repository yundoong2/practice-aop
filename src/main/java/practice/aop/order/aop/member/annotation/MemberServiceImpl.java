package practice.aop.order.aop.member.annotation;

import org.springframework.stereotype.Component;
import practice.aop.order.aop.member.MemberService;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
