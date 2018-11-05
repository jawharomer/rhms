package com.joh.rhms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.joh.rhms.model.Doctor;
import com.joh.rhms.model.Patient;
import com.joh.rhms.model.PatientVisit;
import com.joh.rhms.model.VisitReference;
import com.joh.rhms.service.DoctorService;
import com.joh.rhms.service.PatientService;
import com.joh.rhms.service.PatientVisitService;
import com.joh.rhms.service.VisitReferenceService;

@Controller()
@RequestMapping(path = "/patientVisits")
public class PatientVisitController {

	private static final Logger logger = Logger.getLogger(PatientVisitController.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private PatientVisitService patientVisitService;

	@Autowired
	private DoctorService doctorService;

	@ModelAttribute
	public void addDoctors(Model model) {
		Iterable<Doctor> doctors = doctorService.findAll();
		model.addAttribute("doctors", doctors);
	}

	@GetMapping()
	public String getAllPatientVisit(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllDoctorPatientVisits->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<PatientVisit> patientVisits = patientVisitService.findAllByVisitDateBetween(from, to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "allPatientVisits";
	}

	@GetMapping(path = "/doctors/{id}")
	public String getAllDoctorPatientVisit(@PathVariable int id,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllDoctorPatientVisits->fired");
		logger.info("id=" + id);

		Doctor doctor = doctorService.findOne(id);
		logger.info("doctor=" + doctor);

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<PatientVisit> patientVisits = patientVisitService.findAllDoctorPatientVisit(id, from, to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		model.addAttribute("doctor", doctor);

		return "patientVisits";
	}

	@GetMapping(path = "/add/{patientId}")
	public String getAddingPatientVisit(@PathVariable int patientId, Model model) throws JsonProcessingException {
		logger.info("getAddingPatientVisit->fired");
		logger.info("patientId=" + patientId);

		ObjectMapper mapper = new ObjectMapper();

		Patient patient = patientService.findOne(patientId);

		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPatient(patient);

		Iterable<Doctor> doctors = doctorService.findAll();

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
		model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));

		return "addPatientVisit";
	}

	@PostMapping(path = "/add")
	public String addPatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result, Model model,
			HttpServletResponse response) throws JsonProcessingException {
		logger.info("addPatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("errors" + result.getAllErrors());

		if (result.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			ObjectMapper mapper = new ObjectMapper();

			Iterable<Doctor> doctors = doctorService.findAll();

			model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
			model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));

			return "addPatientVisit";

		} else {
			patientVisitService.save(patientVisit);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingPatientVisit->fired");
		logger.info("id=" + id);

		ObjectMapper mapper = new ObjectMapper();

		PatientVisit patientVisit = patientVisitService.findOne(id);
		logger.info("patientVisit=" + patientVisit);

		Iterable<Doctor> doctors = doctorService.findAll();

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
		model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));

		return "editPatientVisit";
	}

	@PostMapping(path = "/update")
	public String updatePatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result, Model model,
			HttpServletResponse response) throws JsonProcessingException {
		logger.info("updatePatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("errors" + result.getAllErrors());

		if (result.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			ObjectMapper mapper = new ObjectMapper();

			Iterable<Doctor> doctors = doctorService.findAll();

			model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));
			model.addAttribute("jsonDoctors", mapper.writeValueAsString(doctors));

			return "editPatientVisit";

		} else {
			patientVisitService.save(patientVisit);
			return "success";
		}

	}

}
