package com.greatlearning.EmployeeManagementSystem.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.EmployeeManagementSystem.entity.User;
import com.greatlearning.EmployeeManagementSystem.repository.UserRepository;
import com.greatlearning.EmployeeManagementSystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	@Transactional
	public Optional<User> findById(long theId) {
		return userRepository.findById(theId);
	}

	@Override
	@Transactional
	public void save(User theUser) {
		userRepository.save(theUser);
	}

	@Override
	@Transactional
	public void deleteById(long theId) {
		userRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public Optional<User> findByUserName(String userName) {
		return Optional.ofNullable(userRepository.getUserByUsername(userName));
	}

}
