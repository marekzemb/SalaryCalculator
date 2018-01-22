Annotation based ReST web aplication. 

Technologies used: Spring Boot, JOOQ, MySQL, Maven. 

How to run:
1. Create SALARY_CALC schema in your MySQL database.
2. Execute create-table script from resources/tables.SQL in your database.
3. Go to your project's directory and run 'mvn clean install' to generate JOOQ's files. (you need to have maven installed)
4. Send HTTP requests with POSTMAN or other API.
