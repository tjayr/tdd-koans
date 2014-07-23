package com.liberty.it.driving.license.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DrivingLicenseService {

	private Map<String, List<String>> database = new ConcurrentHashMap<>();

	public DrivingLicenseService() {
		List<String> both = new ArrayList<>();
		both.add("Truck");
		both.add("Car");

		List<String> car = new ArrayList<>();
		car.add("Car");

		database.put("John_Jones", both);
		database.put("Peter_Smith", car);
		database.put("Franklin_Roosevelt", car);

	}

	public List<String> findLicenseByName(String firstName, String lastName) {
		return database.getOrDefault(firstName + "_" + lastName,
				new ArrayList<>());
	}

	protected void addLicense(String firstName, String lastName,
			List<String> licenseTypes) {
		database.put(firstName + "_" + lastName, licenseTypes);
	}
	
	
}
