package com.liberty.it.employee.database.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

	private Employee employee;

	@Before
	public void setup() {
		employee = new Employee();
		employee.setDob(LocalDate.now());
		employee.setFirstName("firstName");
		employee.setLastName("lastName");
		employee.setLicensesHeld(new ArrayList<String>());
	}

	@Test
	public void testHashCode() {
		assertEquals(employee.hashCode() , -857881915);
	}

	@Test
	public void testEqualsObject() {
		Employee emp = new Employee();
		emp.setDob(LocalDate.now());
		emp.setFirstName("firstName");
		emp.setLastName("lastName");
		emp.setLicensesHeld(new ArrayList<String>());
		assertEquals(employee, emp);
		emp.setLastName("last");
		assertNotSame(employee, emp);
	}

	@Test
	public void testToString() {
		assertEquals(
				"Employee [firstName=firstName, lastName=lastName, licensesHeld=[], dob=2014-07-23]",
				employee.toString());
	}

}
