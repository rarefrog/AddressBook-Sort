package ch.bbw.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.bbw.addressbook.Address;
import ch.bbw.addressbook.LastnameFirstnameRegistrationDatecomparator;

public class TestComparator {

	private List<Address> addresses;
	
	@Before
	public void setup() {		
		addresses = new ArrayList<>();
		addresses.add(new Address(1, "Petra", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(2, "Peter", "Muster", "000 0000 0000", new Date()));
		addresses.add(new Address(3, "Fritz", "Müller", "000 0000 0000", new Date()));
		addresses.add(new Address(4, "Dario", "Andres", "000 0000 0000", new Date()));
	}
	
	@Test
	public void testSort() {
		Comparator<Address> comparator = new LastnameFirstnameRegistrationDatecomparator();
		assertEquals(0, comparator.compare(addresses.get(0), addresses.get(0)));

		assertTrue(comparator.compare(addresses.get(0), addresses.get(1)) > 0);
	}

}
