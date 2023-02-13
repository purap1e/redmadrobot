package com.example.bootcamp;

import com.example.bootcamp.entities.Role;
import com.example.bootcamp.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BootcampApplication implements ApplicationRunner {

    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BootcampApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new Role(1L,"ROLE_USER"),
                    new Role(2L,"ROLE_MANAGER"),
                    new Role(3L,"ROLE_ADMIN")));
        }
    }
}
