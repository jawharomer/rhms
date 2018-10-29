package com.joh.rhms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.rhms.dao.PatientVisitDAO;
import com.joh.rhms.model.PatientVisit;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {

	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Override
	public List<PatientVisit> findAllDoctorPatientVisit(int id, Date from, Date to) {
		return patientVisitDAO.findAllByDoctorIdAndVisitDateBetween(id, from, to);

	}

}
