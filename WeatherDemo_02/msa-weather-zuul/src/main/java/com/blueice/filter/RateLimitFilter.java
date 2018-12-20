package com.blueice.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 限流过滤
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    //令牌桶 google-guava 工具包，参数100表示，每秒钟产生多少个令牌.
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1000);

    @Override
    public String filterType() {
        //类型为前置过滤
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器的位置
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果没有拿到令牌
        if(!RATE_LIMITER.tryAcquire()){
            throw new RuntimeException();
        }
        return null;
    }
}
