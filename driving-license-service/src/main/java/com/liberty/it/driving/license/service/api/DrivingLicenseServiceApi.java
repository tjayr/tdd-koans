package com.liberty.it.driving.license.service.api;

import java.util.List;

public interface DrivingLicenseServiceApi {

	public List<String> findLicenseByName(String firstName, String lastName);

}