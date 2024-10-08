package com.hyperion.interceptors;

import com.hyperion.pojo.Result;
import com.hyperion.pojo.User;
import com.hyperion.utils.JwtUtil;
import com.hyperion.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token=request.getHeader("Authorization");
        try {
            Map<String,Object> claims= JwtUtil.parseToken(token);
            //把业务数据存到ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            //http状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空线程变量
        ThreadLocalUtil.remove();
    }
}
