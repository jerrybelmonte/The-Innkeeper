package test;

import static org.junit.jupiter.api.Assertions.*;

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
		tenants = new TenantList();
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
		List<Tenant> tenantsToAdd = Arrays.asList(
				new Tenant("Mike Roberts", 102),
				new Tenant("Amy Johnson", 103),
				new Tenant("Dave Rodirguez", 104),
				new Tenant("Julia Howard", 105),
				new Tenant("Joe Beckham", 106),
				new Tenant("Roger Torres", 107),
				new Tenant("Mia Bustamante", 108),
				new Tenant("Karen Page", 109),
				new Tenant("Jose Gonzales", 110),
				new Tenant("Alan Turin", 111),
				new Tenant("Harry Potter", 112),
				new Tenant("Abigail Richardson", 113),
				new Tenant("Alex Castellanos", 114),
				new Tenant("Rick Sanchez", 115),
				new Tenant("John Ramos", 116),
				new Tenant("Lucy Zepeda", 117),
				new Tenant("Tracy Hernandez", 118),
				new Tenant("Regina Wong", 119),
				new Tenant("Darwin Gomez", 120)
				);
		
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
			tenants.recordTenants(filename);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}


	@Test
	@Order(6)
	void testDeleteTenants() {
		tenants.deleteTenants();
		tenants = null;
		
		assertNull(tenants);
	}


	@Test
	@Order(7)
	void testCreateTenantListNormalConstructor() {
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
		
		System.out.println(tenants.displayTenants());
	}


	@Test
	@Order(8)
	void testCreateTenantListOverloadedConstructor() {
		String tenantRecords = "201 Aaa Zzz\n" 
				+ "202 Bbb Yyy\n" + "203 Ccc Xxx\n" + "204 Ddd Www\n"
				+ "205 Eee Vvv\n" + "206 Fff Uuu\n" + "207 Ggg Ttt\n" 
				+ "208 Hhh Sss\n" + "209 Iii Rrr\n" + "210 Jjj Qqq\n";
		
		tenants = new TenantList(tenantRecords);
		
		assertNotNull(tenants);
		
		System.out.println(tenants.displayTenants());
	}

} // End of the AddNewTenantTest class.
