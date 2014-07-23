package com.liberty.it.driving.license.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DrivingLicenseServiceTest {

	private static final List<String> VALID_LICENSE_DATA = new ArrayList<>();
	static {
		VALID_LICENSE_DATA.add("Truck");
		VALID_LICENSE_DATA.add("Car");
	}

	@Test
	public void testFindLicenseByName() {
		DrivingLicenseService dls = new DrivingLicenseService();
		List<String> result = dls.findLicenseByName("John", "Jones");
		assertEquals(VALID_LICENSE_DATA, result);
		assertTrue(dls.findLicenseByName("Nobody", "").isEmpty());
	}

	@Test
	public void testAddLicense() {
		DrivingLicenseService dls = new DrivingLicenseService();
		dls.addLicense("A", "B", VALID_LICENSE_DATA);
		assertEquals(VALID_LICENSE_DATA, dls.findLicenseByName("A", "B"));
	}

}
