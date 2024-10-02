package com.springMVC.Spring.MVC.services;

import com.springMVC.Spring.MVC.dto.EmployeeDTO;
import com.springMVC.Spring.MVC.entities.EmployeeEntity;
import com.springMVC.Spring.MVC.exceptions.ResourceNotFoundException;
import com.springMVC.Spring.MVC.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * when map is called on an Optional, it only applies the mapping function if the Optional is non-empty (i.e., the employeeEntity is not null). If employeeEntity is null, the map method does nothing and will return an empty Optional<EmployeeDTO>
     * @param id to get desired employee
     * @return Optional<EmployeeDTO>
     */
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public Optional<List<EmployeeDTO>> getEmployees() {
        return Optional.of(employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList()));
    }
    public Optional<EmployeeDTO> createEmployee(EmployeeDTO employeeDTO) {
        return Optional.of(modelMapper.map(employeeRepository.save(modelMapper.map(employeeDTO, EmployeeEntity.class)), EmployeeDTO.class));
    }
    public Optional<EmployeeDTO> updateEmployeeById(EmployeeDTO employeeDTO, Long id) {
        isExistsByEmployeeId(id);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return Optional.ofNullable(modelMapper.map(savedEntity, EmployeeDTO.class));
    }
    public Boolean deleteEmployeeById(Long id) {
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }
    public EmployeeDTO updateEmployeePartiallyById(Map<String, Object> updates, Long id) {
        isExistsByEmployeeId(id);
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) {
            throw new ResourceNotFoundException("Employee not found with id : "+id);
        }
    }
}
