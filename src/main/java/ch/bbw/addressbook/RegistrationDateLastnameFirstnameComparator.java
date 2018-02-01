package ch.bbw.addressbook;

import java.util.Comparator;

public class RegistrationDateLastnameFirstnameComparator implements Comparator<Address> {

	 @Override
	 public int compare(Address o1, Address o2) {
		int c;
		
		c=o1.getRegistrationDate().compareTo(o2.getRegistrationDate());
		if (c == 0)
			c = o1.getLastname().compareTo(o2.getLastname());
		if (c == 0)
			c=o1.getFirstname().compareTo(o2.getFirstname());
			return c;
		}
	}