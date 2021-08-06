# User Service

UserAPI is a Java based SpringBoot RESTful API designed to provide user registration and vendor switching functionality
for an online buy now pay later provider. Accessing the service using a variety of POST calls provides a simple way to access the
service and to allow for scalability in line with the growth of the business. This service uses JPA and
Hibernate for database functionality and management.

## Installation

Java 11 or higher is required. All other dependencies are managed within the build.gradle file.

## Usage

The service can be initialised through the UserApiApplication class or by running

```bash
gradle bootRun
```

Whilst running the service can be hit through a CURL request, a sample of which can be found below.

Register-user:

```
curl --location --request POST 'http://localhost:8080/register-user' \
--header 'x-auth-token: vD08cTfRi7' \
--header 'Content-Type: application/json' \
--data-raw '{
    "emailAddress": "bethany@aol.com",
    "password": "thisispassword",
    "newsFeaturesAgreed": true,
    "firstName": "Bethany",
    "lastName": "loong",
    "gender": "FEMALE",
    "birthDay": "09",
    "birthMonth": "08",
    "birthYear": "1995",
    "address": {
        "doorNumber": 9,
        "streetName": "Orchard",
        "county": "HERTSt",
        "postCode": "AL8 7PL"
    },
    "cardDetails": {
        "longCardNumber": "1234567891234567",
        "expiryDate": "0421",
        "cvv": "099",
        "balance": 100
    },
    "stores": {
    },
    "dtype": "test"
}'
```
Vendor-switch:

```
curl --location --request POST 'http://localhost:8080/vendor-switch' \
--header 'x-auth-token: vD08cTfRi7' \
--header 'userID: 34' \
--header 'Content-Type: application/json' \
--data-raw '{
    "asos": true,
    "asda": false,
    "ebay": false
}'
```

## Additional Functionality

This service was created with a view to implement the rest of the functionality required to provide a complete payment journey. 
