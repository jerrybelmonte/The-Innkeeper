package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		this.apartments = new HashMap<>();
	} // End of the default constructor.


	/**
	 * Normal constructor for the TenantList class.
	 * 
	 * @param list The list of tenants.
	 */
	public TenantList(List<Tenant> list) {
		this.tenants = new TreeSet<>(list);
		this.apartments = new HashMap<>();
		this.apartments = list.stream().map(t -> {
			return Map.entry(t.getTenantName(), t.getApartmentNumber());
		 	}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	} // End of the normal constructor.


	/**
	 * Deletes the list of tenants.
	 */
	public void deleteTenants() {
		this.tenants.clear();
		this.apartments.clear();
	} // End of the deleteTenants method


	/**
	 * Adds a new tenant to the list of tenants.
	 * 
	 * @param newTenant The new tenant to add.
	 */
	public void addTenant(Tenant newTenant) {
		if (this.tenants.add(newTenant)) {
			try {
				this.apartments.putIfAbsent(newTenant.getTenantName(), 
						newTenant.getApartmentNumber());
			} catch (NullPointerException NPE) {}
		} //end if
	} // End of the insertTenant method


	/**
	 * Returns the apartment number of the given tenant name if and only if
	 * the tenant is in the list of current tenants.
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
	 */
	public String displayTenants() {
		if (!this.tenants.isEmpty()) {
			
			String newline = System.lineSeparator();
			StringBuilder sb = new StringBuilder();
			Iterator<Tenant> iter = this.tenants.iterator();
			
			while (iter.hasNext()) {
				Tenant temp = iter.next();
				sb.append(temp.getApartmentNumber());
				sb.append(", ");
				sb.append(temp.getTenantName());
				sb.append(newline);
			} //end while
			
			return sb.toString();
		} //end if
		else {
			return null;
		} //end else
	} // End of the displayTenants method
	
} // End of the TenantList class.
