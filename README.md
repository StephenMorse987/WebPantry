# Web Pantry

## Features
Users can:
- [x] Log in to the WebPantry System
  - [x] Log in with a username / password unique to each user.
    - [x] Create a new user with unique login data.
- [x] Access a pantry unique to them
  - [x] Be informed that the pantry is empty
  - [x] Insert items into the pantry
  - [x] Remove items from the pantry
  - [ ] Compare items from a given recipe and create a shopping list from missing items

## Usage
1. Create a **docker** image using the **dockerfile** in `lib\src\main\resources`
   - run a container with that image
1. Compile the library using: `gradle build`
   - run `lib\build\lib\lib.war` on a server (I used apache tomcat)
1. Access the server's localhost location. (I will use `localhost:8080`)
   - `localhost:8080/lib/`

## License
MIT

## Author
Stephen Morse
