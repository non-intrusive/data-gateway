package cn.snaildev.zuul.gateway.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-08 17:44
 **/
@Slf4j
public class NetUtils {
    public static final String UNKOWN = "unkown";
    public static final String X_Forwarded_For = "X-Forwarded-For";
    public static final String Proxy_Client_IP = "Proxy-Client-IP";
    public static final String WL_Proxy_Client_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    /**
     * 获取请求客户端Ip
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
//        Enumeration<String> headerNames = request.getHeaderNames();
//        StringBuilder sb  =new StringBuilder();
//        while(headerNames.hasMoreElements()){
//            String name = (String) headerNames.nextElement();
//            sb.append(name);
//            sb.append(":");
//            sb.append(request.getHeader(name));
//            sb.append("    ");
//        }
//        log.info(String.format("request headers info: %s", sb.toString()));

        String ip = request.getHeader(X_Forwarded_For);
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(Proxy_Client_IP);
        }
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_Proxy_Client_IP);
        }
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_X_FORWARDED_FOR);
        }
        if (ip == null || ip.length() == 0 || UNKOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.info(String.format("get ip from method getRemoteAddress() %s", ip));
        }
        return ip;
    }
}

