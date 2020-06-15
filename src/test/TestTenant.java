package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Tenant;

/**
 * Unit test for the Tenant class.
 * 
 * @author Jerry Belmonte
 */
class TestTenant {
	Tenant tenant = null;
	String name = "Some Name";
	int number = 101;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		tenant = new Tenant(name, number);
	}

	@AfterEach
	void tearDown() throws Exception {
		tenant = null;
	}


	@Test
	void testTenantHashCode() {
		int hashCode = Objects.hash(number, name);
		
		assertEquals(hashCode, tenant.hashCode());
	}


	@Test
	void testTenantNormalConstructor() {
		String theirName = "John";
		int theirApartment = 102;
		
		Tenant newTenant = new Tenant(theirName, theirApartment);
		
		assertNotNull(newTenant);
		assertEquals(theirName, newTenant.getTenantName());
		assertEquals(theirApartment, newTenant.getApartmentNumber());
	}


	@Test
	void testGetTenantName() {
		assertEquals(name, tenant.getTenantName());
	}


	@Test
	void testSetTenantName() {
		String nameToSet = "John";
		
		tenant.setTenantName(nameToSet);
		
		assertEquals(nameToSet, tenant.getTenantName());
	}


	@Test
	void testGetApartmentNumber() {
		assertEquals(number, tenant.getApartmentNumber());
	}


	@Test
	void testSetApartmentNumber() {
		int numberToSet = 103;
		
		tenant.setApartmentNumber(numberToSet);
		
		assertEquals(numberToSet, tenant.getApartmentNumber());
	}


	@Test
	void testEqualsObject() {
		Tenant sameTenant = tenant;
		Tenant duplicateTenant = new Tenant(name, number);
		Tenant differentTenant = new Tenant("Other Name", 110);
		
		assertFalse(sameTenant.equals(null));
		assertTrue(tenant.equals(sameTenant));
		assertTrue(duplicateTenant.equals(tenant));
		assertFalse(tenant.equals(differentTenant));
	}


	@Test
	void testCompareTo() {
		Tenant equalTenant = new Tenant(name, number);
		Tenant lessThanTenant = new Tenant("Less Than", 90);
		Tenant greaterThanTenant = new Tenant("Greater Than", 200);
		
		assertEquals(0, tenant.compareTo(equalTenant));
		assertEquals(-1, lessThanTenant.compareTo(tenant));
		assertEquals(1, greaterThanTenant.compareTo(tenant));
	}


	@Test
	void testToString() {
		String str = number + ", " + name;
		
		assertEquals(str, tenant.toString());
	}

} // End of the TestTenant class.
