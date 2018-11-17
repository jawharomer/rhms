package com.joh.rhms.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.rhms.model.Appointment;
import com.joh.rhms.model.Doctor;
import com.joh.rhms.service.AppointmentService;
import com.joh.rhms.service.DoctorService;

@Controller()
@RequestMapping(path = "/appointments")
public class AppointmentController {

	private static final Logger logger = Logger.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DoctorService doctorService;

	@ModelAttribute
	public void addDoctors(Model model) {
		Iterable<Doctor> doctors = doctorService.findAll();
		model.addAttribute("doctors", doctors);
	}

	@GetMapping(path = "/doctors/{id}")
	public String getAllDoctorAppointment(@PathVariable int id,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllDoctorAppointment->fired");
		logger.info("id=" + id);

		Doctor doctor = doctorService.findOne(id);
		logger.info("doctor=" + doctor);

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<Appointment> appointments = appointmentService.findAllByDoctorIdAndDateBetween(id, from, to);

		logger.info("appointments=" + appointments);

		model.addAttribute("appointments", appointments);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		model.addAttribute("doctor", doctor);

		//
		LocalDate start = LocalDate.now().minusDays(2);
		LocalDate end = LocalDate.now().plusDays(12);
		List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
				.limit(ChronoUnit.DAYS.between(start, end)).collect(Collectors.toList());
		model.addAttribute("dates", dates);
		//

		return "appointments";
	}

	@GetMapping(path = "/add/doctors/{id}")
	public String getAddingAppointment(@PathVariable int id, Model model) {
		logger.info("getAddingAppointment->fired");
		logger.info("doctorId=" + id);

		Doctor doctor = doctorService.findOne(id);

		Appointment appointment = new Appointment();
		appointment.setDoctor(doctor);

		model.addAttribute("appointment", appointment);

		return "appointment/addAppointment";
	}

	@PostMapping(path = "/add")
	public String addAppointment(@RequestBody @Valid Appointment appointment, BindingResult result, Model model) {
		logger.info("addAppointment->fired");

		logger.info("appointment=" + appointment);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("appointment", appointment);

			return "appointment/addAppointment";
		} else {
			appointmentService.save(appointment);

			return "success";
		}
	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingAppointment(@PathVariable int id, Model model) {
		logger.info("getEditingAppointment->fired");
		logger.info("id=" + id);

		Appointment appointment = appointmentService.findOne(id);

		model.addAttribute("appointment", appointment);

		return "appointment/editAppointment";
	}

	@PostMapping(path = "/update")
	public String updateAppointment(@RequestBody @Valid Appointment appointment, BindingResult result, Model model) {
		logger.info("addAppointment->fired");

		logger.info("appointment=" + appointment);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("appointment", appointment);

			return "appointment/editAppointment";
		} else {
			appointmentService.update(appointment);
			return "success";
		}
	}

	@PostMapping(path = "/delete/{id}")
	public String deleteAppointment(@PathVariable int id) {
		logger.info("deleteAppointment->fired");
		logger.info("id=" + id);

		appointmentService.delete(id);
		return "success";
	}

}
