package com.joh.rhms.service;

import com.joh.rhms.model.Doctor;

public interface DoctorService {

	Iterable<Doctor> findAll();

	Doctor findOne(int id);
}
