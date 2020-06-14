package main;
// Sirage
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public class RentalIncomeReport {
	//TODO: columns and rows
	private TreeSet<IncomeRecord> records;
	private TreeMap<Integer,String> tmap;

	public RentalIncomeReport()
	{
		this.records = new TreeSet<>();
		this.tmap = new TreeMap<Integer,String>();
	}
	
	public RentalIncomeReport(TreeSet<IncomeRecord> records) {
		this.records = records;
	}
	
	public void recordIncomePayment(String rent) {
		//TODO: method to record a rent record
	}
}
