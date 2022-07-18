package com.greatlearning.EmployeeManagementSystem.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagementSystem.entity.Roles;
import com.greatlearning.EmployeeManagementSystem.repository.RoleRepository;
import com.greatlearning.EmployeeManagementSystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public List<Roles> findAll() {
		List<Roles> roles = roleRepository.findAll();
		return roles;
	}

	@Override
	@Transactional
	public Optional<Roles> findById(int theId) {
		return roleRepository.findById(theId);
	}

	@Override
	@Transactional
	public void save(Roles theRole) {
		roleRepository.save(theRole);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		roleRepository.deleteById(theId);
	}

	@Override
	public Optional<Roles> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// @Transactional 
	// public Optional<Roles> findByName(String name) {
	// return Optional.ofNullable(roleRepository.getRoleByName(name));
	// }

}
