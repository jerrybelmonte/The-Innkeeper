package main;
// Sirage
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RentalIncomeReport {
	//TODO: columns and rows
	private List<IncomeRecord> records;
	private Map<Integer,String> tmap;

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
	
	public Map<Integer,String> getTmap()
	{
		return tmap;
	}
	
	public List<IncomeRecord> getRecords()
	{
		return records;
	}

	public void addRecord(IncomeRecord newRecord) 
	{
		if (this.records.add(newRecord)) {
			try {
				this.tmap.putIfAbsent(newRecord.getApartmentN(),
						newRecord.recordRent());
			} catch (NullPointerException NPE) {}
		} //end if
		else {
			throw new IllegalArgumentException("The apartment is occupied");
		} //end else
	}

	public String displayRecord()
	{
		if (!this.records.isEmpty()) {

			String newline = System.lineSeparator();
			StringBuilder sb = new StringBuilder();
			Iterator<IncomeRecord> iter = this.records.iterator();

			for (Map.Entry<Integer, String> entry : tmap.entrySet())
			{
				sb.append("Apartment# " + entry.getKey());
				sb.append(entry.getValue());
				sb.append(newline);
			}
			//sb.append(tmap.values());
			return sb.toString();
		}
		else {
			return null;
		}
	}
	
	public void recordIncomePayment(String rent) {
		//TODO: method to record a rent record
		
	}

}
