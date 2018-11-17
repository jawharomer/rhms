package com.joh.rhms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.joh.rhms.model.Appointment;

public interface AppointmentDAO extends CrudRepository<Appointment, Integer> {
	List<Appointment> findAllByDoctorIdAndDateBetween(int id, Date from, Date to);
}
