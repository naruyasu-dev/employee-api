package com.example.employee_api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.employee_api.model.Employee;

@Mapper
public interface EmployeeMapper {

    List<Employee> findAll();

    Employee findById(Long id);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Long id);

    List<Employee> search(@Param("keyword") String keyword,
                          @Param("departmentId") Long departmentId);

    int count();
}