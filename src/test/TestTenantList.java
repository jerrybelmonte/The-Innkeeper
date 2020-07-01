package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	tenants = TenantList.getInstance();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
	System.out.println("Tearing down test case.");
    }

    @BeforeEach
    void setUp() throws Exception {
	System.out.println("Starting test.");
    }

    @AfterEach
    void tearDown() throws Exception {
	String tenantList = tenants.displayTenants();
	if (tenantList != null) {
	    System.out.print(tenantList);
	}
    }

    @Test
    @Order(1)
    void testTenantListDefaultConstructor() {
	assertNotNull(tenants);
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
