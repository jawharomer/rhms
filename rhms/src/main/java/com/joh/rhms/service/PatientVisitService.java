package com.joh.rhms.service;

import java.util.Date;
import java.util.List;

import com.joh.rhms.model.PatientVisit;

public interface PatientVisitService {

	List<PatientVisit> findAllDoctorPatientVisit(int id, Date from, Date to);

}
