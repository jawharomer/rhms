package com.joh.rhms.service;

import java.util.Date;
import java.util.List;

import com.joh.rhms.model.PatientVisit;

public interface PatientVisitService {

	List<PatientVisit> findAllDoctorPatientVisit(int id, Date from, Date to);

	PatientVisit save(PatientVisit patientVisit);

	List<PatientVisit> findAllByVisitDateBetween(Date from, Date to);

	PatientVisit findOne(int id);

}
