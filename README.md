**Overview:**


Big basket is a well known online platform for buying groceries from stores in the locality. Our webapp is based on a similar idea.We have attempted to build a simplified version of bigbasket.

There are basically 2 types of users who can access the portal:
1. Customer
2. Admin

Functionalities for a user with customer level access:
a. Explore the catalog page.
b. Add items to the order Via catalog page.
c. View previous order history

Functionalities for a user with admin level access:
a. Add/update items in the catalog page.
b. View user details (those who are registered on the platform).
c. Change Access status of the user (customer-> admin and vice versa)

**Technical Description:**

We have used the Spring framework to build our project. The architecture employed is microservice architecture along with layered architecture.

The project is primarily structured in 4 layers:
1.Presentation layer:
  Techstack : spring MVC (Model View Controller)


2. API Gateway:
  ZUUL (API gateway developed by netflix, integrated into apache ecosystem).


3.Microservice layer:
  Spring boot (exposed functionalities using REST APIs developed on spring boot)
  We have created 3 microservices: User, Catalog, Order.
    a. User : Creates, authenticates a user during signup/login process.
    b. Catalog: Maintains list of all items available for sale, exposes modification functionality to admin user via RESTful API.
    c. Order: Provides ordering functionality to user, uses order table to provide other functionaities related to order (Eg- view order history).
    
4. Database layer: RDBMS (MySQL)
![alt text](https://github.com/[username]/[reponame]/blob/[branch]/image.jpg?raw=true)
