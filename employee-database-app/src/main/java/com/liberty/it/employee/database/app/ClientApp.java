package com.liberty.it.employee.database.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liberty.it.driving.license.service.DrivingLicenseService;
import com.liberty.it.driving.license.service.api.DrivingLicenseServiceApi;

public class ClientApp {

	private static String readValue(String prompt) {
		System.out.print(prompt);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String value = null;

		try {
			value = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}
		return value;
	}

	public static void main(String[] args) {
		DrivingLicenseServiceApi licenseService = new DrivingLicenseService();
		DataManager manager = new DataManager(licenseService);
		String fname = readValue("First name: ");
		String lname = readValue("Last name: ");
		String dob = readValue("Date of birth (yyyy-mm-dd): ");
		String licenseTypes = readValue("Licenses Held (comma separated list of CAR, TRUCK): ");

		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate dateOfBirth = LocalDate.parse(dob);
		String[] lt = licenseTypes.split(",");

		Employee emp = manager.createEmployee(fname, lname, dateOfBirth,
				Arrays.asList(lt));
		System.out.print(emp);
		System.exit(0);
	}

}
