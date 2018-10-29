package com.joh.rhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.rhms.model.Doctor;

public interface DoctorDAO extends CrudRepository<Doctor, Integer> {

}
