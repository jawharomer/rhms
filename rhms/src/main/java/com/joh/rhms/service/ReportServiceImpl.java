package com.joh.rhms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.rhms.dao.ReportDAO;
import com.joh.rhms.domain.model.NotificationD;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDAO reportDAO;
	
	
	@Override
	public List<NotificationD> findAdminNotifications() {
		return reportDAO.findAdminNotifications();
	}
	
	
}
