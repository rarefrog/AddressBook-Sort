package ch.bbw.addressbook;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AddressService {
	
	@Inject
	@Named("AddressDAO_Memory")
	private AddressDAO addressDAO;
	private Comparator<Address> comparator;
	
	
	public List<Address> getAllAddresses() {
		List<Address> addresses = addressDAO.readAll();

		// TODO: Hier Sortierung einbauen
		
		// TODO: Order by lastname
//		// Einfache Sortiervariante ohne externen eigenständigen Comparator
//		addresses.sort( (a1, a2) -> { 
//			return a1.getLastname().compareTo(a2.getLastname()); 
//		});
		
		addresses.sort(comparator);
		return addresses;
	}
	
	public void registerAddress(Address address) {
		// TODO: Hier Registrierungsdatum setzen
		address.setRegistrationDate(new Date());
		addressDAO.create(address);
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public Comparator<Address> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Address> comparator) {
		this.comparator = comparator;
	}

}
