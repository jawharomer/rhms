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

	@Override
	public PatientVisit save(PatientVisit patientVisit) {
		return patientVisitDAO.save(patientVisit);

	}

	@Override
	public List<PatientVisit> findAllByVisitDateBetween(Date from, Date to) {
		return patientVisitDAO.findAllByVisitDateBetween(from, to);

	}

	@Override
	public PatientVisit findOne(int id) {
		return patientVisitDAO.findOne(id);

	}

	@Override
	public void delete(int id) {
		patientVisitDAO.delete(id);

	}

}
