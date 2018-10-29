package com.joh.rhms.service;

import java.util.Date;

import com.joh.rhms.model.Patient;

public interface PatientService {

	Iterable<Patient> findAll(Date from, Date to);

	Patient save(Patient patient);

	Patient findOne(int id);

}
