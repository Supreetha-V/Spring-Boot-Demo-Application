# Savings Goal Application

## Description
This application implements the 'Round-Up' feature for Bank customers using the Starling Bank public developer API.
This project is built as a personal project to demonstrate the implementation of a practical use-case using 
spring-boot framework.

The application implements the following functionalites:
1. It fetches all the accounts and account details such as the accountId, type and amount details.
2. All the settled transactions per account for a given week are fetched. These transactions are rounded up to their 
   nearest pound and the total round up difference is calculated per account.
3. This calculated round up difference is transferred to a newly created savings goal.

## Pre-Requisites 
1. To test the application sign-up for the Starling developer account [here](https://developer.starlingbank.com/signup).
2. Create an API application [here](https://developer.starlingbank.com/application/list).
3. Create a sandbox customer for the application [here](https://developer.starlingbank.com/sandbox/select).
4. Click auto-simulate button to create 30 transactions on the customer account.
5. Copy the access token of the customer to run the application.

## Build and execution
The project can be built and executed locally using the steps outlined below:
1. git clone https://github.com/Supreetha-V/SavingsGoalService_Supreetha-Venkatesha.git
2. Run the application SavingsGoalApplication.
3. Send POST request to "http://localhost:8080/savings-goal/create/{start-date}" endpoint with a valid 
   start date and authentication token to create a savings goal and add the round up difference amount from the 
   transaction feed to the savings goal.
   For example: curl -X POST http://localhost:8080/savings-goal/create/2022-07-15T17:20:36.123Z -H 'token: {accessTokenForSandBoxAPI}'


