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
 * 前置过滤
 */
@Component
public class TokenFilter extends ZuulFilter {

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
        //是否拦截过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取请求
        HttpServletRequest request = requestContext.getRequest();

        //从参数中获取token,也可从cookies,header中获取。
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false); //请求不通过。
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()); //设置状态码为权限不足
        }
        return null;
    }
}
