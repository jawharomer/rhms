package com.joh.rhms.service;

import java.util.List;

import com.joh.rhms.domain.model.NotificationD;

public interface ReportService {

	List<NotificationD> findAdminNotifications();

}
