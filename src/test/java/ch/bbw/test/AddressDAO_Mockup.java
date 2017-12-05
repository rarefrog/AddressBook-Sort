package ch.bbw.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.bbw.addressbook.Address;
import ch.bbw.addressbook.AddressDAO;


public class AddressDAO_Mockup implements AddressDAO {
		
	private List<Address> addresses;
	
	public AddressDAO_Mockup() {		
		addresses = new ArrayList<>();
		addresses.add(new Address(1, "Petra", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(2, "Peter", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(3, "Fritz", "Müller", "000 0000 0000", new Date()));
		addresses.add(new Address(4, "Dario", "Andres", "000 0000 0000", new Date()));	
	}
	
	// CRUD Commands: Create Read Update Delete
	public void create(Address address) {
		addresses.add(address);
	}
	
	public Address read(int id) {
		return addresses.get(id-1);
	}
	public List<Address> readAll() {
		return addresses;
	}

	public void update(Address address) {
		// TODO: update, not implemented yet
	}
	
	public void delete(int id) {
		// TODO: delete, not implemented yet
	}

}
