package com.joh.rhms.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.rhms.model.Patient;
import com.joh.rhms.model.VisitReference;
import com.joh.rhms.service.PatientService;
import com.joh.rhms.service.VisitReferenceService;

@Controller()
@RequestMapping(path = "/patients")
public class PatientController {

	private static final Logger logger = Logger.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private VisitReferenceService visitReferenceService;

	@GetMapping()
	public String getAllPatient(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllPatient->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<Patient> patients = patientService.findAll(from, to);

		logger.info("patients=" + patients);

		model.addAttribute("patients", patients);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patients";
	}

	@GetMapping(path = "/add")
	public String getAddingPatient(Model model) throws JsonProcessingException {
		logger.info("getAddingPatient->fired");

		ObjectMapper mapper = new ObjectMapper();

		Iterable<VisitReference> visitReferences = visitReferenceService.findAll();

		Patient patient = new Patient();

		model.addAttribute("jsonPatient", mapper.writeValueAsString(patient));
		model.addAttribute("jsonVisitReferences", mapper.writeValueAsString(visitReferences));

		return "addPatient";
	}

	@PostMapping(path = "/add")
	public String addPatient(@RequestBody Patient patient) {
		logger.info("addPatient->fired");

		logger.info("patient=" + patient);

		return "addPatient";
	}

}
