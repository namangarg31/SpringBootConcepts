package com.springMVC.Spring.MVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springMVC.Spring.MVC.annotations.EmployeeRoleValidation;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    @Email
    private String email;
    @EmployeeRoleValidation
    private String role;
    @Min(value = 18, message = "Student's age should not be less than 18")
    @Digits(integer = 10, fraction = 0, message = "Student's age must be a whole number")
    private Integer age;
    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;
}
