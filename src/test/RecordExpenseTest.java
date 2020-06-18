package test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.ExpensePaymentReport;
import main.ExpenseRecord;

class RecordExpenseTest {
	static String filename = "expense.txt";
	static ExpensePaymentReport expense = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path path = FileSystems.getDefault().getPath("", filename);
		filename = path.toAbsolutePath().toString();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Reading the tenants text file...");
		Path source = Paths.get(filename);
		if (Files.exists(source)) {
			for (String record : Files.readAllLines(source)) {
				System.out.println(record);
			}
		} else {
			System.out.println("File does not exist.");
		}
		System.out.println("Tearing down test case.");
	}

	@BeforeEach
	void setUp() throws Exception {
		expense = null;
	}

	@AfterEach
	void tearDown() throws Exception {
		if (expense != null) {
			System.out.println(expense);
		}
	}

	@Test
	void testExpenseRecordStringConstructor() {
		String record = "6,18,Utilities,City Water,100.0";
		ExpenseRecord expRecord = new ExpenseRecord(record);
		
		assertNotNull(expRecord);
		assertEquals(record, expRecord.recordExpense());
		
		System.out.println(expRecord);
		
		String str = expRecord.recordExpense();
		ExpenseRecord expCopy = new ExpenseRecord(str);
		
		assertNotNull(expCopy);
		assertEquals(record, expCopy.recordExpense());
		
		System.out.println(str);
	}

	@Test
	void testExpensePaymentReportListOfExpenseRecord() {
		List<ExpenseRecord> list = List.of(
				new ExpenseRecord(1, 10, "Utilities", "City Water", 100), 
				new ExpenseRecord(2, 20, "Insurance", "All State", 200), 
				new ExpenseRecord(3, 30, "Repairs", "Bob's Plumbing", 300)
				);
		
		expense = new ExpensePaymentReport(list);
		
		assertNotNull(expense);
	}

	@Test
	void testExpensePaymentReportString() {
		String[] arrOfStrings = {
				"4,5,Repairs,Bob's Plumbing,150.5", 
				"5,15,Utilities,City Water,1200", 
				"6,25,Insurance,State Farm,1800"
				};
		String strList = String.join("\n", arrOfStrings);
		
		expense = new ExpensePaymentReport(strList);
		
		assertNotNull(expense);
	}

	@Test
	void testRecordExpensePayment() {
		List<ExpenseRecord> list = List.of(
				new ExpenseRecord(1, 10, "Utilities", "City Water", 100), 
				new ExpenseRecord(2, 20, "Insurance", "All State", 200), 
				new ExpenseRecord(3, 30, "Repairs", "Bob's Plumbing", 300)
				);
		
		expense = new ExpensePaymentReport(list);
		
		try { expense.recordExpensePayment(filename); }
		catch (IllegalArgumentException e) { fail(e.getMessage()); }
	}

	@Test
	void testDisplayExpenseCategories() {
		String[] arrOfStrings = {
				"4,5,Repairs,Bob's Plumbing,150.5", 
				"5,15,Utilities,City Water,1200", 
				"6,25,Insurance,State Farm,1800"
				};
		String strList = String.join("\n", arrOfStrings);
		
		expense = new ExpensePaymentReport(strList);
		
		try { expense.recordExpensePayment(filename); }
		catch (IllegalArgumentException e) { fail(e.getMessage()); }
	}

}
