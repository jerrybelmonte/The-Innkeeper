package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
 * Unit test for the Use case: Add New Tenant
 *
 * @author Jerry Belmonte
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddNewTenantTest {
    static String filename = "tenants.txt";
    static TenantList tenants = null;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
	Path path = FileSystems.getDefault().getPath("", filename);
	filename = path.toAbsolutePath().toString();
	tenants = TenantList.getInstance();
	System.out.println("Setting up test case.");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
	System.out.println("Reading the tenants text file...");
	Path source = Paths.get(filename);
	if (Files.exists(source)) {
	    List<String> lines = Files.readAllLines(source);
	    for (String record : lines) {
		System.out.println(record);
	    }
	} else {
	    System.out.println("File does not exist.");
	}
	System.out.println("Tearing down test case.");
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    @Order(1)
    void testTenantListInstance() {
	assertNotNull(tenants);
	assertNull(tenants.displayTenants());
    }

    @Test
    @Order(2)
    void testAddTenant() {
	String theirName = "Bob Smith";
	int theirApartment = 101;
	Tenant firstTenant = new Tenant(theirName, theirApartment);

	tenants.addTenant(firstTenant);

	assertNotNull(firstTenant);
	assertNotNull(tenants.getTenant(theirName));
    }

    @Test
    @Order(3)
    void testAddMoreTenants() {
	List<Tenant> tenantsToAdd = Arrays.asList(new Tenant("Mike Roberts", 102), new Tenant("Amy Johnson", 103),
		new Tenant("Dave Rodirguez", 104), new Tenant("Julia Howard", 105), new Tenant("Joe Beckham", 106),
		new Tenant("Roger Torres", 107), new Tenant("Mia Bustamante", 108), new Tenant("Karen Page", 109),
		new Tenant("Jose Gonzales", 110), new Tenant("Alan Turin", 111), new Tenant("Harry Potter", 112),
		new Tenant("Abigail Richardson", 113), new Tenant("Alex Castellanos", 114),
		new Tenant("Rick Sanchez", 115), new Tenant("John Ramos", 116), new Tenant("Lucy Zepeda", 117),
		new Tenant("Tracy Hernandez", 118), new Tenant("Regina Wong", 119), new Tenant("Darwin Gomez", 120));

	for (Tenant next : tenantsToAdd) {
	    tenants.addTenant(next);
	}

	assertNotNull(tenants.displayTenants());
    }

    @Test
    @Order(4)
    void testDisplayTenants() {
	String list = tenants.displayTenants();

	assertNotNull(list);

	System.out.println(list);
    }

    @Test
    @Order(5)
    void testRecordTenants() {
	try {
	    tenants.recordTenants();
	} catch (IllegalArgumentException e) {
	    fail(e.getMessage());
	}
    }

} // End of the AddNewTenantTest class.
