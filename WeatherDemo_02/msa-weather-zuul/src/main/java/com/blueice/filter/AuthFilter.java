package com.blueice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限拦截
 */
@Component
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //此过滤器的位置，在PRE_DECORATION_FILTER_ORDER过滤器之前。
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取请求
        HttpServletRequest request = requestContext.getRequest();

        //如果请求链接与给定的链接相同则不拦截。
        if("/api/user/xxx".equals(request.getRequestURL())){
            return false;
        }
        //否则拦截
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // todo
        // 如果拦截，返回用户无权限。
        return null;
    }
}
