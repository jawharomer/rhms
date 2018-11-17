package com.joh.rhms.service;

import java.util.Date;
import java.util.List;

import com.joh.rhms.model.Appointment;

public interface AppointmentService {

	Iterable<Appointment> findAll();

	List<Appointment> findAllByDoctorIdAndDateBetween(int id, Date from, Date to);

	Appointment save(Appointment appointment);

	Appointment findOne(int id);

	Appointment update(Appointment appointment);

	void delete(int id);

}
