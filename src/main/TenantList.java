package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * The list of tenants in the apartment building.
 * 
 * @author Jerry Belmonte
 */
public class TenantList {
	/**	Set of Tenant objects. */
	private Set<Tenant> tenants;
	/**	Map of tenant name keys to apartment number values. */
	private Map<String, Integer> apartments;


	/**
	 * Default constructor for the TenantList class.
	 */
	public TenantList() {
		this.tenants = new TreeSet<>();
		this.apartments = new TreeMap<>();
	} // End of the default constructor.


	/**
	 * Normal constructor for the TenantList class.
	 * 
	 * @param list The list of tenants.
	 */
	public TenantList(List<Tenant> list) {
		this.tenants = new TreeSet<>(list);
		this.apartments = new TreeMap<>();
		this.apartments = list.stream().map(t -> {
			return Map.entry(t.getTenantName(), t.getApartmentNumber());
		 	}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	} // End of the normal constructor.


	/**
	 * Overloaded constructor for the TenantList class.
	 * 
	 * @param array The string array of tenant records.
	 */
	public TenantList(String[] array) {
		this.tenants = new TreeSet<>();
		this.apartments = new TreeMap<>();
		
		List<Tenant> list = Arrays.stream(array).map(str -> {
			String line = str.trim();
			int index = line.indexOf(" ");
			String name = line.substring(index + 1);
			int aptNum = Integer.valueOf(line.substring(0, index));
			return new Tenant(name, aptNum);
		}).collect(Collectors.toList());
		
		list.forEach(t -> this.addTenant(t));
		
		//for (Tenant element : list) {
		//	this.addTenant(element);
		//}
	} // End of the overloaded constructor.


	/**
	 * Returns the tenant with the given name argument if there is a tenant with 
	 * that name on the list.
	 * 
	 * @param tenantName The name of the tenant you want to get.
	 * @return The tenant you are searching for if and only if the tenant is in 
	 * 		   the list of tenants. Otherwise, returns null.
	 */
	public Tenant getTenant(String tenantName) {
		if (this.apartments.containsKey(tenantName)) {
			
			Tenant tenant= null;
			int apartmentNumber = this.getApartmentNumber(tenantName);
			Tenant tenantSearchingFor = new Tenant(tenantName, apartmentNumber);
			
			Iterator<Tenant> iter = this.tenants.iterator();
			
			while (iter.hasNext()) {
				
				Tenant tempTenant = iter.next();
				
				if (tempTenant.equals(tenantSearchingFor)) {
					tenant = tempTenant; //found the tenant
					break; //exit loop
				} //end if	
			} //end while
			
			return tenant;
		} //end if
		else {
			return null;
		} //end else
	} // End of the getTenant method

	/**
	 * Deletes the current list of tenants.
	 */
	public void deleteTenants() {
		this.tenants.clear();
		this.apartments.clear();
	} // End of the deleteTenants method


	/**
	 * Adds a new tenant to the list of tenants.
	 * 
	 * @param newTenant The new tenant to add.
	 * 
	 * @throws IllegalArgumentException 
	 * 				If the apartment number is occupied.
	 */
	public void addTenant(Tenant newTenant) {
		if (this.tenants.add(newTenant)) {
			try {
				this.apartments.putIfAbsent(newTenant.getTenantName(), 
						newTenant.getApartmentNumber());
			} catch (NullPointerException NPE) {}
		} //end if
		else {
			throw new IllegalArgumentException("The apartment is occupied");
		} //end else
	} // End of the insertTenant method


	/**
	 * Returns the apartment number of the given tenant name argument if and 
	 * only if the tenant is in the list of current tenants.
	 * 
	 * @param tenantName The name of the tenant.
	 * @return The tenant's apartment number. 
	 * 		   Returns -1 if tenant is not in the list of tenants.
	 */
	public int getApartmentNumber(String tenantName) {
		if (this.apartments.get(tenantName) != null) {
			return this.apartments.get(tenantName);
		} //end if
		else {
			return -1;
		} //end else
	} // End of the getApartmentNumber method


	/**
	 * Displays the list of tenants.
	 * 
	 * @return A string with the list of tenants. 
	 * 		   Returns null if the list is empty.
	 */
	public String displayTenants() {
		if (!this.tenants.isEmpty()) {
			
			String newline = System.lineSeparator();
			StringBuilder sb = new StringBuilder();
			Iterator<Tenant> iter = this.tenants.iterator();
			
			while (iter.hasNext()) {
				Tenant temp = iter.next();
				sb.append(temp);
				sb.append(newline);
			} //end while
			
			return sb.toString();
		} //end if
		else {
			return null;
		} //end else
	} // End of the displayTenants method


	public void recordTenants(String filename) {
		Path target = Paths.get(filename).toAbsolutePath();
		String list = this.displayTenants();
		
		try (BufferedWriter bw = Files.newBufferedWriter(target)) {
			bw.write(list);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	} // End of the recordTenants method
	
} // End of the TenantList class.