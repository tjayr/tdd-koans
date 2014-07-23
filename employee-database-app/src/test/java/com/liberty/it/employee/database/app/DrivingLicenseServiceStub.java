package com.liberty.it.employee.database.app;

import java.util.Arrays;
import java.util.List;

import com.liberty.it.driving.license.service.api.DrivingLicenseServiceApi;

public class DrivingLicenseServiceStub implements DrivingLicenseServiceApi {

	@Override
	public List<String> findLicenseByName(String firstName, String lastName) {
		return Arrays.asList("CAR", "TRUCK");
	}

}
