package com.joh.rhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.rhms.model.PatientVisit;

public interface PatientVisitDAO extends CrudRepository<PatientVisit, Integer> {
	List<PatientVisit> findAllByDoctorIdAndVisitDateBetween(int id, Date from, Date to);

	List<PatientVisit> findAllByVisitDateBetween(Date from, Date to);

}
