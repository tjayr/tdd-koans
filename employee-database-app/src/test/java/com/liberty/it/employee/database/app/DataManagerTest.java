package com.liberty.it.employee.database.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.liberty.it.driving.license.service.DrivingLicenseService;

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

	private List<String> allLicenses = new ArrayList<String>();
	private List<String> carLicenses = new ArrayList<String>();
	private Employee testEmployee;

	@Mock
	private DrivingLicenseService drivingLicenseService;

	@Before
	public void setup() {
		allLicenses.clear();
		allLicenses.add("TRUCK");
		allLicenses.add("CAR");
		carLicenses.clear();
		carLicenses.add("CAR");

		testEmployee = new Employee();
		testEmployee.setFirstName("Jim");
		testEmployee.setLastName("Jones");
		testEmployee.setLicensesHeld(allLicenses);
		testEmployee.setDob(LocalDate.of(1980, 5, 5));

		when(
				drivingLicenseService.findLicenseByName(any(String.class),
						any(String.class))).thenReturn(allLicenses);
	}

	@Test
	public void testCreateEmployee() {
		DataManager manager = new DataManager(drivingLicenseService);

		Employee result = manager.createEmployee("Jim", "Jones",
				LocalDate.of(1980, 5, 5), allLicenses);
		assertEquals(testEmployee, result);
	}

	@Test(expected = IllegalStateException.class)
	public void testCreateEmployeeFailsDriverHasNoLicense() {
		DataManager manager = new DataManager(drivingLicenseService);
		when(drivingLicenseService.findLicenseByName("Frank", "Grimes"))
				.thenReturn(carLicenses);
		manager.createEmployee("Frank", "Grimes", LocalDate.of(1980, 5, 5),
				carLicenses);
	}

	@Test(expected = IllegalStateException.class)
	public void testCreateEmployeeFailsAsDriverIsTooYoung() {
		DataManager manager = new DataManager(drivingLicenseService);
		Employee result = manager.createEmployee("Jim", "Jones",
				LocalDate.now(), allLicenses);
		assertEquals(testEmployee, result);
	}

	@Test
	public void testGetAllEmployees() {
		DataManager manager = new DataManager(drivingLicenseService);
		manager.createEmployee(testEmployee.getFirstName(),
				testEmployee.getLastName(), testEmployee.getDob(),
				testEmployee.getLicensesHeld());
		Map<String, Employee> all = manager.getAllEmployees();
		assertEquals(1, all.size());
		assertEquals(
				testEmployee,
				all.get(testEmployee.getFirstName()
						+ testEmployee.getLastName()));
	}
	
	@Test
	public void testValidateEmployeeAge() {
		DataManager manager = new DataManager(drivingLicenseService);
		assertTrue(manager
				.isEmployeeOfLegalDrivingAge(LocalDate.of(1980, 5, 5)));
		assertFalse(manager.isEmployeeOfLegalDrivingAge(LocalDate
				.of(2014, 5, 5)));
	}

}
