#### This project was done as part of the final case study project requirement for per scholas java full stack development training. 
## Description 
##### The online bookstore is a spring boot application that allows customers to add books to their cart for checkout. 
The application also allows website admin to add/delete/edit books category and books for customers to checkout. 
The Customer user can login or sign up. For new customer they can sign up using their information right from the sign-up page, or can use google account to sign in. When new customer user is created, the user details will be saved in the database. New customer user can login and then add books to their cart for checkout. When a new customer user login using their Gmail, the user details such as name and email will be saved in the database. When new user is created using the application, details such as password, email, name, etc.  will be saved in the database. However, the password will be encoded by the spring boot’s BCryptPasswordEncoder. 

Admin: Role
Admin user can: 
-	ADD BOOK CATEGORY, DELETE BOOK CATEGORY, EDIT BOOK CATEGORY
-	ADD NEW BOOK, DELETE BOOK(S), EDIT BOOK

![image](https://user-images.githubusercontent.com/81042610/123123674-de042e00-d3fb-11eb-86aa-f21bad4f050d.png)
![image](https://user-images.githubusercontent.com/81042610/123123316-98dffc00-d3fb-11eb-89f1-0f80543fbad4.png)

CUSTOMER USER: ROLE
CUSTOMER USER CAN:
-	VIEW LIST OF BOOKS BY CATEGORIES 
-	VIEW INDIVIDUAL BOOK FOR MORE INFORMATION
-	ADD BOOKS TO CART
-	CHECKOUT BOOKS THAT ARE IN THE CART
-	REMOVE BOOK(S) THAT ARE IN THE CART
-	NAVIGATE TO THE HOME, SHOP, LOGOUT, or CART ONCE THEY ARE logged in 
-	USER CAN SIGN UP/ OR LOGIN USING GMAIL

![image](https://user-images.githubusercontent.com/81042610/123125532-70f19800-d3fd-11eb-9bd3-ca7d65d45414.png)
![image](https://user-images.githubusercontent.com/81042610/123125552-74851f00-d3fd-11eb-82ac-9b0dade8581a.png)


![image](https://user-images.githubusercontent.com/81042610/123125855-c0d05f00-d3fd-11eb-9c0b-a54a1123a4bb.png)

![image](https://user-images.githubusercontent.com/81042610/123126089-f5441b00-d3fd-11eb-89bb-b37c312b506d.png)

 
## TECHNOLOGY USED / TECHNOLOGY INTEGRATION
- JAVA, thymeleaf,BOOTSTRAP, MARIA DB, SPRING BOOT, GOOGLE Authentication

## ENTITY RELATIONSHIP DIAGRAM 

![image](https://user-images.githubusercontent.com/81042610/123129190-9af88980-d400-11eb-81c0-de58169c29f1.png)

https://trello.com/b/kckAaf4I/online-book-store-final-project

SQL SCRIPT TO RUN TO TEST THE APPLICATION

INSERT INTO roles(id, name) VALUES (1, 'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO users(id,email,password,firstName, lastName) VALUES (1, 'admin@gmail.com', '$2a$10$zcLTgky7L7zAgzu48coQy.Eq/idmRlNXU7vHRADS.1I2z0U8RzH3W','ADMIN','Kedar');

-- PASSWORD FOR admin@gmail.com IS apple100

INSERT INTO user_role(user_id, role_id) VALUES (1,1), (1,2);

-- user data below

INSERT INTO users(id,email,password,firstName, lastName) VALUES (35, 'user@gmail.com', '$2a$10$zcLTgky7L7zAgzu48coQy.Eq/idmRlNXU7vHRADS.1I2z0U8RzH3W','USER','KedUser');

-- PASSWORD FOR user@gmail.com IS apple100

INSERT INTO user_role(user_id, role_id) VALUES (35,2);


