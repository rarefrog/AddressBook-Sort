package ch.bbw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ch.bbw.addressbook.Address;

public class TestAddress {

	@Before
	public void setup() {		
	}
	
	@Test
	public void testConstructor1() {
		Address address = new Address(1, "Peter", "Muster", "000 0000 0000");
		assertEquals(address.getId(), 1);
		assertTrue(address.getFirstname().equals("Peter"));
		assertTrue(address.getLastname().equals("Muster"));
		assertTrue(address.getPhonenumber().equals("000 0000 0000"));
		assertNull(address.getRegistrationDate());
	}
	
	@Test
	public void testConstructor2() {
		Date date = new Date();
		Address address = new Address(1, "Peter", "Muster", "000 0000 0000", date);
		assertEquals(address.getId(), 1);
		assertTrue(address.getFirstname().equals("Peter"));
		assertTrue(address.getLastname().equals("Muster"));
		assertTrue(address.getPhonenumber().equals("000 0000 0000"));
		assertNotNull(address.getRegistrationDate());
		assertTrue(address.getRegistrationDate().equals(date));
	}

}
