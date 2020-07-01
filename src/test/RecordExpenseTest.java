package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    void testRecordExpensePayment() {
	try {
	    expense.recordExpensePayment();
	} catch (IllegalArgumentException e) {
	    fail(e.getMessage());
	}
    }

    @Test
    void testDisplayExpenseCategories() {
	try {
	    expense.recordExpensePayment();
	} catch (IllegalArgumentException e) {
	    fail(e.getMessage());
	}
    }

}
