/** 
 * @date: 日期
 * @description 这是一个过滤器，打印日志， 由filterChain.doFilter决定要不要下一步
 */


// package com.wiki.filter;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;

// import javax.servlet.*;
// import javax.servlet.http.HttpServletRequest;
// import java.io.IOException;

// @Component
// public class LogFilter implements Filter {

//     private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {

//     }

//     @Override
//     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//             throws IOException, ServletException {
//         // 打印请求信息
//         HttpServletRequest request = (HttpServletRequest) servletRequest;
//         long startTime = System.currentTimeMillis();
//         filterChain.doFilter(servletRequest, servletResponse);
//         String mes = String.format("\n[%s]%s, 远程地址%s，耗时%d ms", request.getMethod(), request.getRequestURI(),
//                 request.getRemoteAddr(), System.currentTimeMillis() - startTime);
//         LOG.info(mes);
//     }
// }
