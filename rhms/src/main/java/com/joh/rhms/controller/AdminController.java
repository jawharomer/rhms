package com.joh.rhms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.rhms.domain.model.NotificationD;
import com.joh.rhms.model.Doctor;
import com.joh.rhms.service.DoctorService;
import com.joh.rhms.service.ReportService;

@Controller()
@RequestMapping(path = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private ReportService reportService;

	@Autowired
	private DoctorService doctorService;

	@ModelAttribute
	public void addDoctors(Model model) {
		Iterable<Doctor> doctors = doctorService.findAll();
		model.addAttribute("doctors", doctors);
	}

	@GetMapping()
	public String getAllNotification(Model model) {
		logger.info("getAllNotification->fired");

		List<NotificationD> notificationDs = reportService.findAdminNotifications();
		model.addAttribute("notificationDs", notificationDs);

		return "notifications";
	}

}
