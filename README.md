# CRMService Getting started

## Pre-requisites for Development

* __JDK 1.8 or later__
* __MySQL/some other__
* __Maven__ (_project is currently made in Eclipse/Spring Tool Suite_)
* __Postman / Curl__

### Getting the project up and running (recommend Eclipse/STS)
---
	1. Open project in favourite IDE
	2. Load all maven dependencies
	3. Start your local MySQL server
	4. Update src/main/resource/application.properties
	5. :shipit:
	

##### Updating application.properties 
---
key | value
-|-
spring.datasource.url | <jdbc:mysql://localhost:<**your_port**>/**yourdbname**>
spring.datasource.username | <**your username**>
spring.datasource.password | <**your password**>
spring.jpa.hibernate.ddl-auto | <**your ddl configuration**> _currently set =create for testing(Create the schema and destroy previous data)_

#### Authentication
##### Due to our ddl configuration, we use the following code in CRMController to add our super_user:
---
```Java
@PostConstruct
public void init() {
        User user = new User(0,"super_user","c757b8bf1d6a0e933c98390ce32276f0",true);
	    List<Role> roles1 = new ArrayList<>();
	    roles1.add(new Role("ROLE_ADMIN"));
	    roles1.add(new Role("ROLE_USER"));
	    user.setRoles(roles1);
	    UserDetailsService.saveUser(user);
}
```
**NOTE: Change DDL to not drop, will mean you shall remove this init function and instead make your admin account directly into DB**
* So to begin making requests, use: **username=super_user&password=c757b8bf1d6a0e933c98390ce32276f0"**
* From there, you can start using the API calls.

#### Request-Mappings
---
##### All permitted requests
---
* **GET** /auth/login?username=<yourusername>&password=<yourpassword>  **-> Login**
* **GET** /auth/logout>  **-> Logout**
##### ADMIN only requests
---
* **GET** /api/users/?start=**Integer**&stride=**Integer**  **-> Returns stride no. of pages from start index**
* **GET** /api/users/  **-> Returns All Users**
* **POST** /api/users/  **-> Create user {Content-Type = application/json}**

```Java
     "username": "test",
        "password" : "test",
        "roles": [
            {
                "role": "ROLE_ADMIN"
            },
            {
                "role": "ROLE_USER"
            }
        ],
        "enabled": true
```
* **PUT** /api/users/{**Integer**} **-> Update user {Content-Type = application/json}**
```Java
{
        "username": "test",
        "enabled": true,
         "roles": [
            {
                "role": "ROLE_USER" //Demoted :cry:
            }
        ]
}
```
* **DELETE** /api/users/{**Integer**} **-> Delete user**
##### User requests 
---
* **GET** /api/customers/?start=**Integer**&stride=**Integer**  **-> Returns stride no. of pages from start index**
* **GET** /api/customers/  **-> Returns All Customers**
* **POST** /api/customer/  **-> Create customer {Content-Type = application/json}**

```Java
     {
    "firstname": "Tester",
    "lastname": "McTestface",
    "email": "testing.test@gmailcom"
    }
```
* **PUT** /api/customers/{**Integer**} **-> Update Customer {Content-Type = application/json}**
```Java
{
    "firstname": "StillTester",
    "lastname": "McTestFaceTheTester",
    "email": "testing2.test@gmailcom"
}
```
* **DELETE** /api/customers/{**Integer**} **-> Delete customer**
* **PUT** /api/customers/{**Integer**}/photo **->Update/Add photo**
    *  Needs MultipartFile header
* **DELETE** /api/customers/{**Integer**}/photo **-> Delete customer photo**

---
---
---
---
**Keep on sending those requests**
:shipit: :shipit: :shipit: :shipit: :shipit: