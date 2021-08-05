package builders;

import com.example.demo.services.models.UserDetails;

public class RequestBuilder {

    public UserDetails getUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setUserID(1);
        userDetails.setFirstName("Beth");
        userDetails.setLastName("Long");
        userDetails.setEmailAddress("Bethany@email.com");
        return userDetails;
    }
}
