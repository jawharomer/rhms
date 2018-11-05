package com.joh.rhms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.rhms.dao.VisitReferenceDAO;
import com.joh.rhms.model.VisitReference;

@Service
public class VisitReferenceServiceImpl implements VisitReferenceService {

	@Autowired
	private VisitReferenceDAO visitReferenceDAO;

	@Override
	public Iterable<VisitReference> findAll() {
		return visitReferenceDAO.findAll();
	}
}
