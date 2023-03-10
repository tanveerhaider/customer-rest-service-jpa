# customer-rest-service-jpa
Sample Rest service to interact with customer resource using SpringBoot3

Swagger UI or any rest client can be used to invoke these 
services

http://localhost:8080/swagger-ui/index.html#

Following are some of the end points:

Get Customer By Id
http://localhost:8080/api/v1/customers/1

POST Load
{
"emailAddress": "tanveerabc@gmail.com",
"firstName": "Tanveer",
"lastName": "Haider",
"gender": "M",
"cellPhone": "6475151000",
"zipCode": "129",
"city": "Bangalore"
}

PUT
http://localhost:8080/api/v1/customers/1
{
"emailAddress": "tanveerabc@gmail.com",
"firstName": "Tanveer",
"lastName": "Haider",
"gender": "M",
"cellPhone": "6475151000",
"zipCode": "129",
"city": "Bangalore"
}

PATCH

to partially update resource

http://localhost:8080/api/v1/customers/1

updating only zipcode and city
{
"zipCode": "02170",
"city": "Weymouth"
}




