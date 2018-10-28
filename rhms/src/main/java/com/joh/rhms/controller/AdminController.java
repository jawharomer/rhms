package com.joh.rhms.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(path = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	// @Autowired
	// private ReportService reportService;
	//
	// @GetMapping()
	// public String getAllNotification(Model model) {
	// logger.info("getAllNotification->fired");
	//
	// List<NotificationD> notificationDs = reportService.findAdminNotifications();
	// model.addAttribute("notificationDs", notificationDs);
	//
	// return "notifications";
	// }

}
