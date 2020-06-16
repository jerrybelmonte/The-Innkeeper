package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import main.Tenant;
import main.TenantList;

/**
 * Unit test for the TenantList class.
 * 
 * @author Jerry Belmonte
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestTenantList {
	static TenantList tenants = null;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		tenants = new TenantList();
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
	@Order(1)
	void testTenantListDefaultConstructor() {
		assertNotNull(tenants);
	}


	@Test
	@Order(5)
	void testTenantListListOfTenant() {
		List<Tenant> currentTenants = List.of(
				new Tenant("One", 1001),
				new Tenant("Two", 1002),
				new Tenant("Three", 1003),
				new Tenant("Four", 1004),
				new Tenant("Five", 1005),
				new Tenant("Six", 1006),
				new Tenant("Seven", 1007),
				new Tenant("Eight", 1008),
				new Tenant("Nine", 1009),
				new Tenant("Ten", 1010)
				);
		
		tenants = new TenantList(currentTenants);
		
		assertNotNull(tenants);
	}


	@Test
	@Order(6)
	void testGetTenant() {
		String name1 = "One";
		String name2 = "Two";
		String name9 = "Nine";
		String failName = "Some Name";
		
		Tenant tenant1 = tenants.getTenant(name1);
		Tenant tenant2 = tenants.getTenant(name2);
		Tenant tenant9 = tenants.getTenant(name9);
		Tenant failTenant = tenants.getTenant(failName);
		
		assertNotNull(tenant1);
		assertNotNull(tenant2);
		assertNotNull(tenant9);
		assertNull(failTenant);
	}


	@Test
	@Order(7)
	void testDeleteTenants() {
		tenants.deleteTenants();
		
		String nullStr = tenants.displayTenants();
		
		assertNull(nullStr);
	}


	@Test
	@Order(2)
	void testAddTenant() {
		String firstName = "First Tenant";
		int firstApartment = 101;
		String secondName = "Second Tenant";
		int secondApartment = 102;
		String thirdName = "Third Tenant";
		int thirdApartment = 103;
		
		tenants.addTenant(new Tenant(firstName, firstApartment));
		tenants.addTenant(new Tenant(secondName, secondApartment));
		tenants.addTenant(new Tenant(thirdName, thirdApartment));
		
		assertEquals(firstApartment, tenants.getApartmentNumber(firstName));
		assertEquals(secondApartment, tenants.getApartmentNumber(secondName));
		assertEquals(thirdApartment, tenants.getApartmentNumber(thirdName));
	}


	@Test
	@Order(3)
	void testGetApartmentNumber() {
		String firstName = "First Tenant";
		int firstApartment = 101;
		String notInListName = "Some Name";
		
		assertEquals(firstApartment, tenants.getApartmentNumber(firstName));
		assertEquals(-1, tenants.getApartmentNumber(notInListName));
	}


	@Test
	@Order(4)
	void testDisplayTenants() {
		String listString = tenants.displayTenants();
		
		assertNotNull(listString);
	}

} // End of the TestTenantList class.
