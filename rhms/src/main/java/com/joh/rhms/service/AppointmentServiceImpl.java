package com.joh.rhms.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.rhms.dao.AppointmentDAO;
import com.joh.rhms.dao.DoctorDAO;
import com.joh.rhms.model.Appointment;
import com.joh.rhms.model.Doctor;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Override
	public Iterable<Appointment> findAll() {
		return appointmentDAO.findAll();
	}

	@Override
	public List<Appointment> findAllByDoctorIdAndDateBetween(int id, Date from, Date to) {
		return appointmentDAO.findAllByDoctorIdAndDateBetween(id, from, to);
	}

	@Override
	public Appointment save(Appointment appointment) {
		return appointmentDAO.save(appointment);
	}

	@Override
	public Appointment findOne(int id) {
		return appointmentDAO.findOne(id);
	}

	@Override
	public Appointment update(Appointment appointment) {
		if (appointment.getId() == null)
			throw new EntityNotFoundException();
		return appointmentDAO.save(appointment);
	}

	@Override
	public void delete(int id) {
		appointmentDAO.delete(id);
	}

}
