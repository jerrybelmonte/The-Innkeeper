package main;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The list of tenants in the apartment building.
 */
public class TenantList {
    private static volatile TenantList tenantList;
    private static String fileName = "tenants.txt";
    private Map<Integer, Tenant> aptToTenant;
    private Map<String, Tenant> nameToTenant;

    /**
     * Private constructor for the TenantList class.
     */
    private TenantList() {
	this.aptToTenant = new TreeMap<>();
	this.nameToTenant = new HashMap<>();
	this.loadFile();
    } // End of the default constructor.

    /**
     * Used instead of the normal public constructor.
     *
     * @return The one and only TenantList instance.
     */
    public static TenantList getInstance() {
	if (TenantList.tenantList == null) {
	    synchronized (TenantList.class) {
		if (TenantList.tenantList == null) {
		    TenantList.tenantList = new TenantList();
		}
	    }
	}
	return TenantList.tenantList;
    } // End of the getInstance method

    private void loadFile() {
	Path targetFile = FileSystems.getDefault().getPath(fileName);
	fileName = targetFile.toAbsolutePath().toString();

	if (Files.exists(targetFile)) {
	    try {
		List<String> lines = Files.readAllLines(targetFile);
		this.importList(lines);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    } // End of the loadFile method

    private void importList(List<String> listOfTenants) {
	if (!listOfTenants.isEmpty()) {
	    List<Tenant> tenants = listOfTenants.stream().map(str -> new Tenant(str)).collect(Collectors.toList());

	    tenants.forEach(this::addTenant);
	}
    } // End of the importList method

    /**
     * Adds a new tenant to the list of tenants.
     *
     * @param tenant The new tenant to add.
     * @throws IllegalArgumentException If the apartment number is occupied.
     */
    public void addTenant(Tenant tenant) {
	if (tenant == null) {
	    throw new IllegalArgumentException("Tenant argument is null");
	}

	if (!this.aptToTenant.containsKey(tenant.getApartmentNumber())) {
	    try {
		this.aptToTenant.putIfAbsent(tenant.getApartmentNumber(), tenant);
		this.nameToTenant.putIfAbsent(tenant.getTenantName(), tenant);
	    } catch (NullPointerException NPE) {
		// ignore null return values
	    }
	} else {
	    throw new IllegalArgumentException("The apartment is occupied");
	}
    } // End of the addTenant method

    /**
     * Makes a record of the list of tenants by saving the current list of tenants
     * to a text file.
     */
    public void recordTenants() {
	Path targetPath = Paths.get(fileName).toAbsolutePath();
	String listOfTenants = this.displayTenants();

	try (BufferedWriter buffer = Files.newBufferedWriter(targetPath)) {
	    buffer.write(listOfTenants);
	} catch (IOException e) {
	    throw new IllegalArgumentException(e.getMessage());
	}
    } // End of the recordTenants method

    /**
     * Displays the list of tenants.
     *
     * @return A string with the list of tenants. Returns null if the list is empty.
     */
    public String displayTenants() {
	String listOfTenants = null;

	if (!this.aptToTenant.isEmpty()) {
	    StringBuilder sb = new StringBuilder();
	    String newline = System.lineSeparator();

	    for (Integer apartment : this.aptToTenant.keySet()) {
		Tenant tenant = this.aptToTenant.get(apartment);
		sb.append(tenant.toString());
		sb.append(newline);
	    }

	    listOfTenants = sb.toString();
	}

	return listOfTenants;
    } // End of the displayTenants method

    /**
     * Returns the tenant with the given name argument if there is a tenant with
     * that name on the list.
     *
     * @param tenantName The name of the tenant you want to get.
     * @return The tenant you are searching for if and only if the tenant is in the
     *         list of tenants. Otherwise, returns null.
     */
    public Tenant getTenant(String tenantName) {
	return this.nameToTenant.getOrDefault(tenantName, null);
    } // End of the getTenant method

    /**
     * Returns the apartment number of the given tenant name argument if and only if
     * the tenant is in the list of current tenants.
     *
     * @param tenantName The name of the tenant.
     * @return The tenant's apartment number. Returns -1 if tenant is not in the
     *         list of tenants
     */
    public int getApartmentNumber(String tenantName) {
	return this.nameToTenant.containsKey(tenantName) ? this.nameToTenant.get(tenantName).getApartmentNumber() : -1;
    } // End of the getApartmentNumber method

    public String[] getTitlesForTable() {
	String[] titles = { "Apartment #", "Name" };
	return titles;
    } // End of the getTitlesForTable method

    public String[][] getTableData() {
	Set<Integer> aptNumbers = this.aptToTenant.keySet();
	int numberOfTenants = aptNumbers.size();
	int numberOfColumns = 2;
	String[][] data = new String[numberOfTenants][numberOfColumns];

	int index = 0;
	for (Integer number : aptNumbers) {
	    Tenant temp = this.aptToTenant.get(number);
	    int apartmentNumNdx = 0;
	    int tenantNameNdx = 1;
	    data[index][apartmentNumNdx] = String.valueOf(temp.getApartmentNumber());
	    data[index++][tenantNameNdx] = temp.getTenantName();
	}

	return data;
    } // End of the getTableData method

    @Override
    public String toString() {
	if (!this.nameToTenant.isEmpty()) {
	    StringBuilder sb = new StringBuilder();
	    List<Tenant> list = new ArrayList<>(this.nameToTenant.values());

	    String newline = System.lineSeparator();
	    sb.append(String.format("%-5s | %-12s", "Apt #", "Tenant Name"));
	    sb.append(newline);
	    sb.append("----------------------------");

	    list.sort(null);
	    for (Tenant each : list) {
		sb.append(newline);
		sb.append(String.format("%-5d | %-20s", each.getApartmentNumber(), each.getTenantName()));
	    }

	    return sb.toString();
	} else {
	    return "The list of tenants is empty.";
	}
    } // End of the toString override

} // End of the TenantList class.
