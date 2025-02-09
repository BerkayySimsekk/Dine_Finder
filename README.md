# Dine Finder 

A personal project to practice the knowledge I gained from CS 102 using JavaFX. You can either use the application as a restaurant owner or customer. There are multiple existing restaurant owner accounts and a single customer account which can be found in the ListOfUsers class to login. Sign up option can be used as well to create an account of the desired type and login. Since this is a personal project to practice my knowledge, 
there is no database used to store the information of customers and restaurant owners. Thus, the accounts that are created using the application itself are deleted after exiting the application.

## How to Run the Program

A detailed guide can be found to use JavaFX in the following link: https://openjfx.io/openjfx-docs/#install-javafx

## Description

- `Customers` :
    - Can sign up by giving their information regarding username, email, password and address.
    - Can edit their username, email, password and address.
    - Can either search for a specific food or select the existing buttons that represent certain foods.
    - Can sort the restaurants by alphabetical order, their ratings and popularity; popularity means the amount of ratings given.
    - Can choose to see the restaurants that only have a certain price range; if there is at least one item in the restaurant with the price that falls in the given price range, it will show up in the search results.
    - Can choose to see the restaurants that are only found in their city, district or street.
    - Can choose to reset the filter, resetting means showing all of the restaurants in an order by the date they signed up.
    - Can give a rating to the restaurants once.
    - Can see the menu of the restaurant by scrolling down with the mouse wheel while the mouse icon is on the space that displays the menu items.
- `Restaurant Owners` :
    - Can sign up by giving their information regarding username, email, password, name of the restaurant, the address of the restaurant and a short description for the restaurant.
    - Can edit their username, email, password, restaurant name, restaurant address and the description of the restaurant.
    - Can edit their menu by creating a new item or deleting an existing item; to delete the item, every information regarding that item must match exactly.
    - Can use the searching bits of the application the same way a customer does with exception of not being able to give a rating to the restaurants.




