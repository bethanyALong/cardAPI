package builders;

import com.example.demo.models.*;

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

        CardDetails cardDetails = new CardDetails();
        cardDetails.setLongCardNumber("1234567898765432");
        cardDetails.setExpiryDate("0909");
        cardDetails.setCvv("098");
        cardDetails.setBalance(100);
        userDetails.setCardDetails(cardDetails);

        Address address = new Address();
        address.setDoorNumber(5);
        address.setStreetName("Street");
        address.setCounty("COUN");
        address.setPostCode("AL8 7PL");
        userDetails.setAddress(address);

        Stores stores = new Stores();
        userDetails.setStores(stores);

        return userDetails;
    }

    public Stores getStores(){
        Stores stores = new Stores();
        stores.setAsda(true);
        return stores;
    }
}
