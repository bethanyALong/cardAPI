package builders;

import com.example.demo.models.Address;
import com.example.demo.models.GenderEnum;
import com.example.demo.models.UserDetails;

public class RequestBuilder {

    public UserDetails getUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setEmailAddress("test@aol.com");
        userDetails.setPassword("Thisispassword");
        userDetails.setNewsFeaturesAgreed(true);
        userDetails.setUserID(1);
        userDetails.setFirstName("Beth");
        userDetails.setLastName("Long");
        userDetails.setGender(GenderEnum.FEMALE);
        userDetails.setBirthDay("09");
        userDetails.setBirthMonth("10");
        userDetails.setBirthYear("1995");
        userDetails.setLongCardNumber("1234567898765432");
        userDetails.setExpiryDate("0909");
        userDetails.setCvv("098");
        userDetails.setBalance(100);

        Address address = new Address();
        address.setDoorNumber(5);
        address.setStreetName("Street");
        address.setCounty("COUN");
        address.setPostCode("AL8 7PL");
        userDetails.setAddress(address);

        return userDetails;
    }
}
