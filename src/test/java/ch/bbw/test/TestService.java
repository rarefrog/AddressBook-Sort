package ch.bbw.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import ch.bbw.addressbook.Address;
import ch.bbw.addressbook.AddressService;

public class TestService {

	@Before
	public void setup() {
	}

	@Test
	public void testSort() {
		AddressDAO_Mockup addressDAO_Mockup = new AddressDAO_Mockup();
		
		AddressService addressService = new AddressService();
		addressService.setAddressDAO(addressDAO_Mockup);
		
		List<Address> addresses = addressService.getAllAddresses();
		
//		addresses.forEach(a -> System.out.println(a.getLastname() + ", " + a.getFirstname() + " - " + a.getRegistrationDate()));
		
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
		int countBefore,countAfter;
		
		// Service Aufsetzen
		AddressDAO_Mockup addressDAO_Mockup = new AddressDAO_Mockup();
		AddressService addressService = new AddressService();
		addressService.setAddressDAO(addressDAO_Mockup);
		
		// Hinzufügen prüfen
		countBefore = addressService.getAllAddresses().size();
		
		Date dateNow = new Date();
		Address newAddress = new Address(4711, "James", "Madoon", "0000000000");
		addressService.registerAddress(newAddress);

		countAfter = addressService.getAllAddresses().size();
		assertTrue(countAfter == countBefore+1);

		// Registrierungsdatum prüfen
//		Lösung 1:
//		List<Address>addresses = addressService.getAllAddresses();
//		for (Address a : addresses) {
//			if (a.getId()==newAddress.getId()) {
//				newAddress = a;
//				break;
//			}
//		}
		// Lösung mit Java 8
		newAddress = addressService.getAllAddresses().stream()
				.filter(a -> a.getId() == 4711)
				.collect(Collectors.toList())
				.get(0);
		assertTrue(newAddress.getRegistrationDate().getTime() >= dateNow.getTime());
		
	}
}
