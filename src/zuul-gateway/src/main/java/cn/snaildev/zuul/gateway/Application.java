package cn.snaildev.zuul.gateway;

import cn.snaildev.zuul.gateway.filter.CollectFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-08 16:52
 **/
@Slf4j
@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("***>>>>>>>Server startup>>>");
    }

    @Bean
    public CollectFilter getCollectionFilter() {
        return new CollectFilter();
    }
}
