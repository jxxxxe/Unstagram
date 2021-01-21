package co.kr.datapia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UnstagramCustomerApplication
{
    public static void main(String[] args) {
        SpringApplication.run(UnstagramCustomerApplication.class, args);
    }
}
