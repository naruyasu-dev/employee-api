package com.example.employee_api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper
public interface EmployeeMapper {

    List<Employee> findAll();

    Employee findById(Long id);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Long id);

    // 動的SQL
    List<Employee> search(@Param("keyword") String keyword,
                          @Param("department") String department);
}