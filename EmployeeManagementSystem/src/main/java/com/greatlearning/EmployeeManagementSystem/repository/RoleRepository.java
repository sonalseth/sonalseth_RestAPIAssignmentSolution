package com.greatlearning.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.EmployeeManagementSystem.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
