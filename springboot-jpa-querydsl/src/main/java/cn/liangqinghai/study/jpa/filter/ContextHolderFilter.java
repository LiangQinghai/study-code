package cn.liangqinghai.study.jpa.filter;

import cn.liangqinghai.study.jpa.common.ContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiangQinghai
 * @Title ContextHolderFilter
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 11:04
 */
public class ContextHolderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContextHolder.tenantCode = 1L;

        filterChain.doFilter(request, response);

    }
}
