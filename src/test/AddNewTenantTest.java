package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Tenant;

/**
 * Unit test for the Use case: Add New Tenant
 * 
 * @author Jerry Belmonte
 */
class AddNewTenantTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}


	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}


	@BeforeEach
	void setUp() throws Exception {
	}


	@AfterEach
	void tearDown() throws Exception {
	}


	@Test
	void testTenantConstructor() {
		String theirName = "Some Name";
		int theirApartment = 100;
		Tenant firstTenant = new Tenant(theirName, theirApartment);
		
		assertNotNull(firstTenant);
	}


	@Test
	void testGetTenantName() {
		String theirName = "Some Name";
		int theirApartment = 100;
		Tenant firstTenant = new Tenant(theirName, theirApartment);

		assertTrue(firstTenant.getTenantName().equals(theirName));
	}


	@Test
	void testSetTenantName() {
		String theirName = "Some Name";
		int theirApartment = 100;
		Tenant firstTenant = new Tenant(theirName, theirApartment);
		firstTenant.setTenantName("Another Name");

		assertTrue(firstTenant.getTenantName().equals("Another Name"));
	}


	@Test
	void testGetApartmentNumber() {
		String theirName = "Some Name";
		int theirApartment = 100;
		Tenant firstTenant = new Tenant(theirName, theirApartment);

		assertEquals(theirApartment, firstTenant.getApartmentNumber());
	}


	@Test
	void testSetApartmentNumber() {
		String theirName = "Some Name";
		int theirApartment = 100;
		Tenant firstTenant = new Tenant(theirName, theirApartment);
		firstTenant.setApartmentNumber(200);

		assertEquals(200, firstTenant.getApartmentNumber());
	}

} // End of the AddNewTenantTest class.
