package com.example.TestingUsingMockito.repositories;

import com.example.TestingUsingMockito.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .name("testUser")
                .email("testUser@gmail.com")
                .salary(10000L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {
        // Arrange
        employeeRepository.save(employee);
        // Act
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());
        // Assert
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {
        // Arrange
        String email = "notPresent.123@gmail.com";
        // Act
        List<Employee> employeeList = employeeRepository.findByEmail(email);
        // Assert
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();
    }
}