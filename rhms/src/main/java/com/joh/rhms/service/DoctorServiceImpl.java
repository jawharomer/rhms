package com.joh.rhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.rhms.dao.DoctorDAO;
import com.joh.rhms.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDAO doctorDAO;

	@Override
	public Iterable<Doctor> findAll() {
		return doctorDAO.findAll();
	}
}
