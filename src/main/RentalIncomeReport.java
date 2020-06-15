package main;
// Sirage
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class RentalIncomeReport {
	//TODO: columns and rows
	public List<IncomeRecord> records;
	private TreeMap<Integer,String> tmap;

	public RentalIncomeReport()
	{
		this.records = new ArrayList<IncomeRecord>();
		this.tmap = new TreeMap<Integer,String>(); 
	}
	
	public RentalIncomeReport(ArrayList<IncomeRecord> records) {
		this.records = new ArrayList<>(records);
		this.tmap = new TreeMap<>();
		this.tmap = (TreeMap<Integer, String>) records.stream().collect(Collectors.toMap(IncomeRecord::getApartmentN, IncomeRecord::recordRent));
	}
	
	public TreeMap<Integer,String> getRentRecord()
	{
		return tmap;
	}
	
	public void addRecord(IncomeRecord newRecord) 
	{
		records.add(newRecord);
	} 
	
	public void recordIncomePayment(String rent) {
		//TODO: method to record a rent record
		
	}

}
