
# **Stock Market Application**

## Introduction
This Spring Boot Web Service Application is for Stock Management, this application provides a user-friendly interface to interact with stock-related data, offering essential operations such as creating new stock data, retrieving all stock information, finding opening range breakouts, and merging candle data.
the key features of this application are

Create Stock Data:
This feature is used for creating Stock data

Get All Stock Data:
This feature fetches all the data which is inserted inside the database

Opening Range Breakout Finder:
This feature uses Opening Breakout Range and gives information to the user about how volatile the market is.

Merge Candle's Data:
This feature allows the user to fetch data with respect to time so the user can merge data with specific time intervals.



## Deployed App
[https://deployed-site.whatever](https://github.com/dcs1997/Investo-Bull)



## Features
List out the key features of your application.
-  To get All Stocks Information
-  To find the highest/Lowest Opening Range Breakout (ORB)
-  Merge the stocks with respect to time.



## Installation & Getting started
Open this application in Eclipse and create a maven project with dependencies such as:
Spring Data JPA
MySQL Driver
Spring Web
Devtool
Note: Spring Security can also be added to this program in the future.



## Usage
Provide instructions and examples on how to use your project.
Open Postman
Fire the POST request and add data respectively
Then By Using the GET method user can fetch data with respect to their needs.
<img width="1440" alt="image" src="https://github.com/dcs1997/Investo-Bull/assets/107552890/88c067ed-fdea-4b82-b648-db96aa6911d7">



## API Endpoints

POST/http://localhost:8080/Stocks - To create a new item

GET/http://localhost:8080/AllStocks - To retrieve all items

GET/ http://localhost:8080/ORB/60 -  To find the ORB

GET/http://localhost:8080/mergeStocks/60 -  Merge stocks with respect to time



## Technology Stack
- Mysql
- SpringBoot
- RestAPI
