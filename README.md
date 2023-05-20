This project is a web application that represent a beauty salon.

**1. Technologies that I am using in this application:**
* Spring framework
* Freemarker
* Amazon webservices: S3, budget
* MySQL
* Docker for quick deployment

**2. Features of this project:**

_End user UI:_
* Homepage
* Services/subservices
* Post
* Booking
* Confirmation mail

_Admin UI:_
* Homepage
* Services/subservices management
* Post Management
* Booking management
* S3 file management

**3. Project deployment steps:**

_Use docker to install database:_
- Install docker
- Browse docker directory:
  cd src/main/webapp/resources/docker
- Run docker compose command
  docker-compose -f docker-compose.yaml up
- After successfully installing the mysql container, use a database workbench to test connection
  _database_: MySQL
  _username_: drduongspa
  _password_: password
  _database name_: drduongspa
  _host_: localhost
  _port_: 3306

**4. On-going development**

- To convert MVC controller into api controller
- To implement UI with Angular framework
- To implement password hashing anda data encryption
- To implement subscription
- To implement user registration
