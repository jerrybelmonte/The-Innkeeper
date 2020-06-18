package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import main.IncomeRecord;
import main.RentalIncomeReport;
import main.Tenant;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordRentTest {
	static String filename = "income.txt";
	static RentalIncomeReport income = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path path = FileSystems.getDefault().getPath("", filename);
		filename = path.toAbsolutePath().toString();
		income = new RentalIncomeReport();
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
	void testRecordRent() {
		Tenant tenant = new Tenant("Bob Smith", 101);
		IncomeRecord rent = new IncomeRecord(tenant);
		
		String record = rent.recordRent();
		
		assertNotNull(record);
		System.out.println(record);
	}
	
	@Test
	@Order(2)
	void testIncomeRecordStringConstructor() {
		String record = "Bob Smith;101;[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]";
		IncomeRecord rent = new IncomeRecord(record);
		
		assertNotNull(rent);
		assertEquals(record, rent.recordRent());
		System.out.println(rent.recordRent());
	}
	
	@Test
	@Order(3)
	void testIncomeRecordFloatListConstructor() {
		Tenant tenant = new Tenant("Bob Smith", 101);
		List<Float> floatList = List.of(
				650.0f, 650.0f, 650.0f, 650.0f, 650.0f, 650.0f, 
				650.0f, 650.0f, 0.0f, 0.0f, 0.0f, 0.0f
				);
		IncomeRecord rent = new IncomeRecord(tenant, floatList);
		
		assertNotNull(rent);
		System.out.println(rent.recordRent());
	}
	
	@Test
	@Order(4)
	void testRentalIncomeReportListConstructor() {
		List<Float> floatList = List.of(
				650.0f, 650.0f, 650.0f, 650.0f, 650.0f, 650.0f, 
				650.0f, 650.0f, 0.0f, 0.0f, 0.0f, 0.0f
				);
		List<IncomeRecord> records = List.of(
				new IncomeRecord(new Tenant("Bob Smith", 101), floatList),
				new IncomeRecord(new Tenant("Mike Roberts", 102), floatList), 
				new IncomeRecord(new Tenant("Amy Johnson", 103), floatList), 
				new IncomeRecord(new Tenant("Dave Rodirguez", 104), floatList), 
				new IncomeRecord(new Tenant("Julia Howard", 105), floatList), 
				new IncomeRecord(new Tenant("Joe Beckham", 106), floatList), 
				new IncomeRecord(new Tenant("Roger Torres", 107), floatList), 
				new IncomeRecord(new Tenant("Mia Bustamante", 108)), 
				new IncomeRecord(new Tenant("Karen Page", 109)), 
				new IncomeRecord(new Tenant("Jose Gonzales", 110)) 
				);
		
		income = new RentalIncomeReport(records);
		
		assertNotNull(income);
		
		System.out.println(income.displayRecord());
	}

	@Test
	@Order(5)
	void testGetRecords() {
		Iterator<IncomeRecord> iter = income.getRecords();
		
		assertNotNull(iter);
		
		while (iter.hasNext()) {
			System.out.println(iter.next().recordRent());
		}
	}
	
	@Test
	@Order(6)
	void testRecordIncomePayment() {
		try {
			income.recordIncomePayment(filename);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@Order(7)
	void testRentalIncomeReportStringArray() {
		String lines = null;
		Path source = Paths.get(filename);
		
		try {
			lines = Files.readString(source);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		income = new RentalIncomeReport(lines);
		
		assertNotNull(income);
		
		System.out.println(income.displayRecord());
	}

} // End of the RecordRentTest class.
