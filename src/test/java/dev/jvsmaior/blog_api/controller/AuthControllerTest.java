package dev.jvsmaior.blog_api.controller;

import dev.jvsmaior.blog_api.dto.security.LoginRequestDTO;
import dev.jvsmaior.blog_api.dto.security.RegistrationRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    private RestTestClient restTestClient;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setClient(){
        restTestClient = RestTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    void shouldCreateUser(){
        restTestClient
                .post()
                .uri("/auth/register")
                .body(new RegistrationRequestDTO("joao@example2.com", "senha123"))
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    void shouldLogin(){
        restTestClient
                .post()
                .uri("/auth/login")
                .body(new LoginRequestDTO("joao@example2.com", "senha123"))
                .exchange()
                .expectStatus()
                .isOk();
    }
}
