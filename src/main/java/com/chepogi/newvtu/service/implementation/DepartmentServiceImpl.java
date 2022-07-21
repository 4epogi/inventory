package com.chepogi.newvtu.service.implementation;

import com.chepogi.newvtu.exceptions.BadRequestException;
import com.chepogi.newvtu.exceptions.DepartmentException;
import com.chepogi.newvtu.model.Department;
import com.chepogi.newvtu.repository.DepartmentRepository;
import com.chepogi.newvtu.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Department> list() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public String saveDepartment(Department newDepartment) {
        String operationResult;
        Optional<Department> duplicateDepartment = departmentRepository.findByNameIgnoreCase(newDepartment.getName());

        if(duplicateDepartment.isPresent()){
            return "there is a department with such name";
        }
        Department department = departmentRepository.save(newDepartment);
        return "operation was successful";
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Department department = findDepartmentById(id);
        departmentRepository.deleteById(id);
    }

    @Override
    public Department findDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        throw new DepartmentException("Department not found");
//        return department.(() -> new DepartmentException("DepartmentNotFound"));
    }

    @Override
    public String updateDepartment(Department department) {
        Optional<Department> duplicateDepartment = departmentRepository.findByNameIgnoreCase(department.getName());
        if(duplicateDepartment.isPresent()){
            return "there is a department with such name";
        }
        Department updatedDepartment = departmentRepository.save(department);
        return "Department has been successfully updated";
    }
}
