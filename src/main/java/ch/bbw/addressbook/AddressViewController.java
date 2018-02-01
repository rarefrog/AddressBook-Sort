package ch.bbw.addressbook;

import java.util.Comparator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddressViewController {
	
	@Inject
	private AddressService addressService;
	
    private String firstname;
    private String lastname;
    private String phoneNumber;
    
    private String message;
    private String whichComp;
    
    
    public AddressViewController() {
    	message = "";
    	whichComp = "sort1";
	}

	private void clearFields() {
        firstname = "";
        lastname = "";
        phoneNumber = "";    	
    }
	
    public void saveAddress() {
        Address address = new Address(0, firstname, lastname, phoneNumber);
        addressService.registerAddress(address);
        message = "The address was saved successfully.";
        clearFields();
    }
    public List<Address> getAddresses() {
    	Comparator<Address> comparator;
    	if (whichComp.equals("sort1")) {
    		comparator = new LastnameFirstnameRegistrationDatecomparator();
    	} else{
    		comparator = new RegistrationDateLastnameFirstnameComparator();
    	}
    	addressService.setComparator(comparator);
        return addressService.getAllAddresses();
    }
    
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String isWhichComp() {
		return whichComp;
	}
	public void setWhichComp(String whichComp) {
		this.whichComp = whichComp;
	}
}