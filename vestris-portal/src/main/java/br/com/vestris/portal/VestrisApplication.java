package br.com.vestris.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.vestris")
// 1. Força a busca por @Entity em todos os módulos
@EntityScan(basePackages = "br.com.vestris")
// 2. Força a busca por Interfaces Repository em todos os módulos
@EnableJpaRepositories(basePackages = "br.com.vestris")
public class VestrisApplication {
    public static void main(String[] args) {
        SpringApplication.run(VestrisApplication.class, args);
    }
}
