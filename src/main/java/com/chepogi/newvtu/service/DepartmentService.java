package com.chepogi.newvtu.service;

import com.chepogi.newvtu.exceptions.DepartmentException;
import com.chepogi.newvtu.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> list();

    String saveDepartment(Department newDepartment);

    void deleteById(Long id) throws DepartmentException;

    Department findDepartmentById(Long id) throws DepartmentException;

    String updateDepartment(Department department);

}
