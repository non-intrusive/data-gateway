package cn.snaildev.zuul.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: Ming.Zhao
 * @create: 2019-04-08 17:40
 **/
@EnableAsync
@Configuration
public class ThreadPoolConfiguration {

    @Value("${pool.filter.CorePoolSize:100}")
    private int filterCorePoolSize = 50;

    @Value("${pool.filter.MaxPoolSize:100}")
    private int filterMaxPoolSize = 100;

    @Value("${pool.filter.QueueCapacity:100}")
    private int filterQueueCapacity = 100;

    @Value("${pool.filter.KeepAlive:30}")
    private int filterKeepAlive = 30;


    @Bean("filterExecutor")
    public ThreadPoolTaskExecutor createThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(filterCorePoolSize);
        executor.setMaxPoolSize(filterMaxPoolSize);
        executor.setQueueCapacity(filterQueueCapacity);
        executor.setThreadNamePrefix("Task-Executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(filterKeepAlive);
        executor.initialize();
        return executor;
    }

}

