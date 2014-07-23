package com.liberty.it.employee.database.app;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liberty.it.driving.license.service.DrivingLicenseService;
import com.liberty.it.driving.license.service.api.DrivingLicenseServiceApi;

public class DataManager {

	private final Map<String, Employee> data = new HashMap<>();

	private DrivingLicenseServiceApi dls;

	public DataManager(DrivingLicenseServiceApi service) {
		this.dls = service;
	}
	
	public DataManager() {		
	}

	public Employee createEmployee(String firstName, String lastName,
			LocalDate dob, List<String> licenses) throws IllegalStateException {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setDob(dob);
		employee.setLicensesHeld(licenses);

		boolean age = isEmployeeOfLegalDrivingAge(dob);
		boolean license = isEmployeeLicensed(firstName, lastName);

		if (!age) {
			throw new IllegalStateException("Employee " + employee
					+ " is not at least 21 years old");
		}

		if (!license) {
			throw new IllegalStateException("Employee " + employee
					+ " does not have a truck license");
		}

		data.put(firstName + lastName, employee);
		return employee;

	}

	public boolean isEmployeeLicensed(String firstName, String lastName) {		
		List<String> licenses = dls.findLicenseByName(firstName, lastName);
		return licenses.contains("TRUCK");
	}

	public boolean isEmployeeOfLegalDrivingAge(LocalDate dob) {
		long result = ChronoUnit.YEARS.between(dob, LocalDate.now());
		return result >= 21;
	}

	public Map<String, Employee> getAllEmployees() {
		return Collections.unmodifiableMap(data);
	}

}
