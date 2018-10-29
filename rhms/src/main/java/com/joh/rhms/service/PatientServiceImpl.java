package com.joh.rhms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.rhms.dao.PatientDAO;
import com.joh.rhms.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDAO patientDAO;

	@Override
	public Iterable<Patient> findAll(Date from, Date to) {
		return patientDAO.findAllByTimeBetweenOrderByTimeDesc(from, to);
	}

	@Override
	public Patient save(Patient patient) {
		return patientDAO.save(patient);
	}
	
	@Override
	public Patient findOne(int id) {
		return patientDAO.findOne(id);
	}
}
