Overview:
=========
Big basket is a well known online platform for buying groceries from stores in the locality. Our webapp is based on a similar idea.We have attempted to build a simplified version of bigbasket.

There are basically 2 types of users who can access the portal:
* Customer
* Admin

Functionalities for a user with customer level access:
* Explore the catalog page.
* Add items to the order Via catalog page.
* View previous order history

Functionalities for a user with admin level access:
* Add/update items in the catalog page.
* View user details (those who are registered on the platform).
* Change Access status of the user (customer-> admin and vice versa)

Technical Description:
----------

We have used the Spring framework to build our project. The architecture employed is microservice architecture along with layered architecture.

The project is primarily structured in 4 layers:
* ### Presentation layer ###:
  * Techstack : spring MVC (Model View Controller)


* ### API Gateway ###:
  * ZUUL (by netflix)


* ### Microservice layer ###:
  Spring boot (exposed functionalities using REST APIs developed on spring boot)
We have created 3 microservices: User, Catalog, Order.
    * User : Creates, authenticates a user during signup/login process.
    * Catalog: Maintains Database layer RDBMS (MySQL)
    * Order : Exposes functionality related to ordering, maintains record of past orders for the user

How does it look?
-----

      
![alt text](https://github.com/swapnilsaxena11/myBigBasket/blob/master/images/homePage.png)

This is the Landing page


![alt text](https://github.com/swapnilsaxena11/myBigBasket/blob/master/images/login.png)

This is the login page, User enters his/her credentials to login.Based on user access level (Customer,Admin) , user is redirected to the respective dashboard


![alt text](https://github.com/swapnilsaxena11/myBigBasket/blob/master/images/login%20successful.png)

If the credentials provided are correct, User is redirected to Dashboard.


![alt text](https://github.com/swapnilsaxena11/myBigBasket/blob/master/images/homePage.png)

This is the menu page, which displays all the items listed on the BigBasket Platform along with its price.User can add item to the cart.


![alt text](https://github.com/swapnilsaxena11/myBigBasket/blob/master/images/calculate%20bill.png)

After proceeding with the order, this page shows the order summary with total sum displayed at the bottom.


![alt text](https://github.com/swapnilsaxena11/myBigBasket/blob/master/images/all%20users.png)

This feature is available only to the users with admin level privilege.Here, the admin can see user details (Username,Admin status,Order History).Admin level user can also toggle the admin status of other users.
