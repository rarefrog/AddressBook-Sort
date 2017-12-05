package ch.bbw.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.bbw.addressbook.Address;
import ch.bbw.addressbook.AddressDAO;
import ch.bbw.addressbook.AddressService;


public class TestServiceWithMockito {
	
	// DAO Schnittstellen Methoden
	//	void create(Address address);
	//	Address read(int id);
	//	List<Address> readAll();
	//	void update(Address address);
	//	void delete(int id);

	private List<Address> addresses;
	private AddressDAO addressDAO;
	
	@Before
	public void setup() {
		addresses = new ArrayList<>();
		addresses.add(new Address(1, "Petra", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(2, "Peter", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(3, "Fritz", "Müller", "000 0000 0000", new Date()));
		addresses.add(new Address(4, "Dario", "Andres", "000 0000 0000", new Date()));
		
		// Mockito Mockup aufsetzen
		addressDAO = Mockito.mock(AddressDAO.class); 
		Mockito.when(addressDAO.readAll()).thenReturn(addresses);
	}


	@Test
	public void testSort() {

		AddressService addressService = new AddressService();
		addressService.setAddressDAO(addressDAO);
		
		List<Address> addresses = addressService.getAllAddresses();
		
		for (int i=0; i<addresses.size()-1; i++) {
			int compLastname = addresses.get(i).getLastname().compareTo(addresses.get(i+1).getLastname());
			assertTrue(compLastname==0 || compLastname<0);
			if (compLastname==0) {
				int compFirstname = addresses.get(i).getFirstname().compareTo(addresses.get(i+1).getFirstname());
				assertTrue(compFirstname==0 || compFirstname<0);
				if (compFirstname==0) {
					int compDate = addresses.get(i).getRegistrationDate().compareTo(addresses.get(i+1).getRegistrationDate());
					assertTrue(compDate==0 || compDate>0);
				}
			}
		}
	}
	
	@Test
	public void testRegisterAddress() {
		
		// Service Aufsetzen
		AddressService addressService = new AddressService();
		addressService.setAddressDAO(addressDAO);
		
		
		// Hinzufügen prüfen	
//		Date dateNow = new Date();
		Address newAddress = new Address(4711, "James", "Madoon", "0000000000");
		addressService.registerAddress(newAddress);
		
		Mockito.verify(addressDAO, Mockito.atLeastOnce()).create(newAddress);

		// TODO: Test RegistrationDate

//		countAfter = addressService.getAllAddresses().size();
//		assertTrue(countAfter == countBefore+1);
//
//		// Registrierungsdatum prüfen
////		Lösung 1:
////		List<Address>addresses = addressService.getAllAddresses();
////		for (Address a : addresses) {
////			if (a.getId()==newAddress.getId()) {
////				newAddress = a;
////				break;
////			}
////		}
//		// Lösung mit Java 8
//		newAddress = addressService.getAllAddresses().stream()
//				.filter(a -> a.getId() == 4711)
//				.collect(Collectors.toList())
//				.get(0);
//		assertTrue(newAddress.getRegistrationDate().getTime() >= dateNow.getTime());
		
	}
}
