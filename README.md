# **Revent Assignment (To-Do App)**
Task Application

## Introduction
This Spring Boot + React Application is build for a Task Management System, this application provides a user-friendly interface. offering essential operations such as SignUp/LogIn for a  user. further user can creating new Tasks, update it's status when done, delete the task when it's work is done, and also with the help of pagination see all of his tasks.

the key features of this application are

SignUp/LogIn:
This feature is used for SignUp for new users & LogIn for Existing one. 

Create Task:
This feature is used for creating Tasks.

Update Task:
This feature is used to change the status from Incomplete to Complete.

Delete Task:
This feature is used for deleting a certain Tasks.

Pagination:
This feature allows the user to fetch data with respect to time so the user can merge data with specific time intervals.



## Deployed App




## Features
List out the key features of your application.
-  SignUp/LogIn
-  Create and Update data
-  Pagination



## Installation & Getting started
Open this application in Eclipse and create a maven project with dependencies such as:
Spring Data JPA
MySQL Driver
Spring Web
Devtool
Note: Spring Security can also be added to this program in the future.



## Usage
Provide instructions and examples on how to use your project.
Open VS-code and launch the front end app.
Open STS and launch the beackend app.
Through any browser You can signUp/LogIn as user.
later add the tasks.
Update task's states when completed.
use next button to browse through all the tasks created.
Then By Using the GET method user can fetch data with respect to their needs.
<img width="1440" alt="image" src="https://github.com/dcs1997/Investo-Bull/assets/107552890/88c067ed-fdea-4b82-b648-db96aa6911d7">



## API Endpoints for User

POST/http://localhost:8080/Users - To create/SignIn a new user

POST/http://localhost:8080/login - To logIn as a user

DELETE/http://localhost:8080/Users/52 -  To delete the user

## API Endpoints for Tasks

POST/http://localhost:8080/Tasks/1(Note this is User_Id) - To create a new Task.

GET/http://localhost:8080/Tasks/1(Note this is User_Id)/page?PageNumber=0 - for pagination

PUT/http://localhost:8080/Tasks/1 - To Update the status of the task.

DELETE/http://localhost:8080/Tasks/1 -  To delete the task.

## Technology Stack
- Mysql
- SpringBoot
- RestAPI
- React
- CSS
- HTML
- JavaScript

