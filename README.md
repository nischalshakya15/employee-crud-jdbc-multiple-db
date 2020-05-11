## Employee CRUD operation using jdbc 
This application demonstrate how we can change database through command line argument while executing the application.

## Prerequisites
* Java 8 or higher
* Maven
* IDE
* Database

## Run the application 
* git clone https://github.com/nischalshakya15/employee-crud-jdbc-multople.git
* Configure the database url, username and password in .env.properties.
* Go through the project directory through command prompt or terminal.

    ``cd employee-crud-jdbc-multiple-db``
* Build the project 
    
    ``mvn package``
    
* Edit the **.env.properties** and set **ACTIVE.DATABASE** to **mysql / postgres / h2**

* Copy the **employeemgmt.sql and employeemgmt-postgres.sql** and **.env.properties** into target folder 

* Run the application. 

    ``java -jar target/employee-crud-jdbc.jar``
    
**Note**: **ACTIVE.DATABASE is an mandatory value which should be specified in .env.properties file**
