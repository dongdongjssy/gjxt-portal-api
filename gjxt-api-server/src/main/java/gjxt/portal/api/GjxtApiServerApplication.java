package gjxt.portal.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class GjxtApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GjxtApiServerApplication.class, args);
        log.info("API服务启动成功");
    }

}
