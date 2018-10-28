package com.joh.rhms.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.joh.rhms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {
	Iterable<Patient> findAllByTimeBetweenOrderByTimeDesc(Date from, Date to);
}
