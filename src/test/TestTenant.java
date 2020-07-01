package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	this.tenant = new Tenant(this.name, this.number);
    }

    @AfterEach
    void tearDown() throws Exception {
	this.tenant = null;
    }

    @Test
    void testTenantHashCode() {
	int hashCode = Objects.hash(this.number, this.name);

	assertEquals(hashCode, this.tenant.hashCode());
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
	assertEquals(this.name, this.tenant.getTenantName());
    }

    @Test
    void testGetApartmentNumber() {
	assertEquals(this.number, this.tenant.getApartmentNumber());
    }

    @Test
    void testEqualsObject() {
	Tenant sameTenant = this.tenant;
	Tenant duplicateTenant = new Tenant(this.name, this.number);
	Tenant differentTenant = new Tenant("Other Name", 110);

	assertFalse(sameTenant.equals(null));
	assertTrue(this.tenant.equals(sameTenant));
	assertTrue(duplicateTenant.equals(this.tenant));
	assertFalse(this.tenant.equals(differentTenant));
    }

    @Test
    void testCompareTo() {
	Tenant equalTenant = new Tenant(this.name, this.number);
	Tenant lessThanTenant = new Tenant("Less Than", 90);
	Tenant greaterThanTenant = new Tenant("Greater Than", 200);

	assertEquals(0, this.tenant.compareTo(equalTenant));
	assertEquals(-1, lessThanTenant.compareTo(this.tenant));
	assertEquals(1, greaterThanTenant.compareTo(this.tenant));
    }

    @Test
    void testToString() {
	String str = this.number + " " + this.name;

	assertEquals(str, this.tenant.toString());
    }

} // End of the TestTenant class.
