# Web Pantry

## Project Description

This is a web app that keeps track of a user's list of food items.

## Technologies Used

* Java Servlets
* Java HTTPSessions
* Apache Tomcat 9
* Bootstrap 4
* _React JS and Spring __not allowed__ for this Project_

## Features

Completed Features
* User Login with Unique Pantry Table
* Add / Remove Items in the Pantry

To-do list:
* Compare to an existing Recipe to the user pantry and determine what items need to be purchased
* Add multiple items from a single page

## Getting Started & Usage

1. Create a **docker** image using the **dockerfile** in `lib\src\main\resources`
   - run a container with that image
1. Compile the library using: `gradle build`
   - run `lib\build\lib\lib.war` on a server (I used apache tomcat)
1. Access the server's localhost location. (I will use `localhost:8080`)
   - `localhost:8080/lib/`

## License

This project uses the following license: [MIT](https://github.com/StephenMorse987/WebPantry/blob/Restructure/LICENSE.md).
