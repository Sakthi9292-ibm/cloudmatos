Feature: Rest API Validation
  
  @API
  Scenario: Test case for GET API 
  Given Client system is pointing to "https://reqres.in/"
  When Client triggers the request to get service "api/users?page" with id "2"
  Then  Server should respond with "200" and values
  |jsonpath|values|
  |"data"|"2"|
  |"data.email"|"janet.weaver@reqres.in"|
  |"data.first_name"|"Janet"|

  