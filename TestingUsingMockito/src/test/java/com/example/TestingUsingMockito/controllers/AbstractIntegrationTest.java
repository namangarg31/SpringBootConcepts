package com.example.TestingUsingMockito.controllers;

import com.example.TestingUsingMockito.dto.EmployeeDto;
import com.example.TestingUsingMockito.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Automatically use H2 for testing
public class AbstractIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    Employee testEmployee = Employee.builder()
            .id(1L)
            .email("testUser@gmail.com")
            .name("testUser")
            .salary(200L)
            .build();

    EmployeeDto testEmployeeDto = EmployeeDto.builder()
            .id(1L)
            .email("testUser@gmail.com")
            .name("testUser")
            .salary(200L)
            .build();

}
