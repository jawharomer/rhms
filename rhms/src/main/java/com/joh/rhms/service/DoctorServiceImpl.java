package com.joh.rhms.service;

import org.springframework.stereotype.Service;

import com.joh.rhms.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Override
	public Iterable<Doctor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Doctor update(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor save(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Autowired
	// private DoctorDAO doctorDAO;
	//
	// @Override
	// public Iterable<Doctor> findAll() {
	// return doctorDAO.findAll();
	// }
	//
	// @Override
	// public Doctor findOne(int id) {
	// return doctorDAO.findOne(id);
	// }
	//
	// @Override
	// public Doctor save(Doctor doctor) {
	// return doctorDAO.save(doctor);
	// }
	//
	// @Override
	// public Doctor update(Doctor doctor) {
	// if (doctorDAO.findOne(doctor.getId()) == null)
	// throw new EntityNotFoundException();
	// return doctorDAO.save(doctor);
	// }
	//
	// @Override
	// public void delete(int id) {
	// doctorDAO.delete(id);
	// }

}
