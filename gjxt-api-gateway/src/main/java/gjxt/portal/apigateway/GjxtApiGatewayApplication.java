package gjxt.portal.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GjxtApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GjxtApiGatewayApplication.class, args);
    }
}
