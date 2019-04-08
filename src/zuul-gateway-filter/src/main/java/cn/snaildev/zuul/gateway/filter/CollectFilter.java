package cn.snaildev.zuul.gateway.filter;

import cn.snaildev.zuul.gateway.util.NetUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-08 17:22
 **/
@Slf4j
public class CollectFilter extends ZuulFilter {

    @Autowired
    private ThreadPoolTaskExecutor filterExecutor;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRouteHost() == null && ctx.get("serviceId") != null && ctx.sendZuulResponse();
    }

    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String uri = ctx.getRequest().getRequestURI();
        String method = ctx.getRequest().getMethod();
        String ip = NetUtils.getIpAddress(ctx.getRequest());
        String queryString = ctx.getRequest().getQueryString();
        String body = charReader(ctx.getRequest());
        AsyncCollect(ip + "\t" + method + "\t" + uri + "\t" + queryString + "\t" + body);

        return null;
    }

    private void AsyncCollect(final String msg) {
        filterExecutor.execute(new Runnable() {
            public void run() {
                log.info(msg);
            }
        });
    }

    private String charReader(HttpServletRequest request) {
        try {
            BufferedReader br = request.getReader();
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
