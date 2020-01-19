package com.example.kaisen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
@Order(1)
public class KaisenGameFilter implements Filter {

    @Autowired
    private HttpSession httpSession;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpServletRequest = (HttpServletRequest) servletRequest;
        var path = httpServletRequest.getServletPath();
        var method = httpServletRequest.getMethod();

        System.out.println("パス:"+path+",HTTP命令:"+method);
        var playerLine = httpSession.getAttribute("playerLine");


        //認証後のパス
        if(path.equals("/BattlePage") && method.equals("POST")){
            //処理を継続
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //セッションがないとき
        if(Objects.isNull(playerLine)) {
            var dispatcher = servletRequest.getRequestDispatcher("GameStartPage");
            dispatcher.forward(servletRequest,servletResponse);
            return;
        }
        //処理を継続
        filterChain.doFilter(servletRequest, servletResponse);
    }
}