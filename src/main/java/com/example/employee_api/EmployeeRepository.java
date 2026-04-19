package com.example.employee_api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByNameContainingAndDepartmentContaining(
			String name, String department);
}
