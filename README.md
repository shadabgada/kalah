# kalah

This is an implementation of [KALAH](https://en.wikipedia.org/wiki/Kalah) game.
#### Running an application

1. `git clone https://github.com/shadabgada/kalah.git`
2. `cd kalah`
3. `mvn spring-boot:run`

Access the application at `http://localhost:8080/`
#### Tech
- Java 11
- SpringBoot
- JSP

#### Things to Improve:
- The tests are missing in current implementation. The Integration and unit tests need to be added.
- save user game history: This will require us to add the database and user management module.
- The UI is implemented using JSP servlet. In order to have better **user experience** and **performance**, we can go with client-side UI frameworks like React/Angular