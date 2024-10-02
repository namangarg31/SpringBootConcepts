package com.springMVC.Spring.MVC.controllers;

import com.springMVC.Spring.MVC.dto.EmployeeDTO;
import com.springMVC.Spring.MVC.exceptions.ResourceNotFoundException;
import com.springMVC.Spring.MVC.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with employee id : "+id));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return employeeService.getEmployees().map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable(name = "employeeId") Long id) {
        return employeeService.updateEmployeeById(employeeDTO, id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with employee id : "+id));
    }
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long id) {
        if (employeeService.deleteEmployeeById(id)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeePartiallyById(@RequestBody Map<String, Object> updates, @PathVariable(name = "employeeId") Long id) {
        EmployeeDTO employeeDTO = employeeService.updateEmployeePartiallyById(updates, id);
        if(employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        }
        return ResponseEntity.notFound().build();
    }

}
