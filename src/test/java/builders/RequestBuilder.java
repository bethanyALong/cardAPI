package builders;

import com.example.demo.services.models.UserDetails;

public class RequestBuilder {

    public UserDetails getUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.id = 1;
        userDetails.first_name = "Beth";
        userDetails.last_name = "Long";
        userDetails.email_address = "Bethany@email.com";
        return userDetails;
    }
}
