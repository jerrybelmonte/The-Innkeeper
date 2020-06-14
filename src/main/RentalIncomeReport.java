package main;
// Sirage
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class RentalIncomeReport {
	//TODO: columns and rows
	private List<IncomeRecord> records;
	private TreeMap<Integer,String> tmap;

	public RentalIncomeReport()
	{
		this.records = new ArrayList<IncomeRecord>();
		this.tmap = new TreeMap<Integer,String>(); 
	}
	
	public RentalIncomeReport(ArrayList<IncomeRecord> records) {
		this.tmap = new TreeMap<>();
		for (IncomeRecord r : records)
		{
			tmap.put(r.getApartmentN(),r.recordRent());
		}
		
	}
	
	public void recordIncomePayment(String rent) {
		//TODO: method to record a rent record
	}
}
