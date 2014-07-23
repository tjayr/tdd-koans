package com.liberty.it.driving.license.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liberty.it.driving.license.service.api.DrivingLicenseServiceApi;

public class DrivingLicenseService implements DrivingLicenseServiceApi {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.liberty.it.driving.license.service.DrivingLicenseServiceApi#
	 * findLicenseByName(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> findLicenseByName(String firstName, String lastName) {
		return database.getOrDefault(firstName + "_" + lastName,
				new ArrayList<>());
	}

	protected void addLicense(String firstName, String lastName,
			List<String> licenseTypes) {
		database.put(firstName + "_" + lastName, licenseTypes);
	}

}
