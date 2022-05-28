@webpage @LoginPage
Feature: Login Page Test Cases
	
  Scenario Outline: Positive cases for username in LoginPage
    Given user is in the webpage "https://pilot.cloudmatos.dev/login"
    When user enters the Email <Email> in Email field
    And user enters the "password123" in the password field
    And user clicks signin button
    Then user should not get the "Email or Password is not correct."

    Examples: 
      | Email                                                                                                                                                                                              |
      | "email1123@g.com"                                                                                                                                                                                  |
      | "validemail@gmail.com"                                                                                                                                                                          |
      | "aslknalsknalksnalksnlknqlkwnlkqwnlqknwlrnkqrlwqkrnqlkwoqiwqowioewqoiwalkaalksnlnalknqioqwoqioqewoqa_{}asasaaqwoiqwoqwqwoqonononvoqiwnoqiwnoiqwnoienqoienqoinewqoiqiwnmoansosnaasoixnao@gmail.com" |
	
	
  Scenario Outline: Negative cases for username in LoginPage
    Given user is in the webpage "https://pilot.cloudmatos.dev/login"
    When user enters the Email <Email> in Email field
    And user enters the "password123" in the password field
    And user clicks signin button
    Then user should get the "Email or Password is not correct."

    Examples: 
      | Email                                                                                                                                                                                                                                                                      |
      | "123123!"                                                                                                                                                                                                                                                                  |
      | "_.@@g.com"                                                                                                                                                                                                                                                                |
      | "@gmail.com"                                                                                                                                                                                                                                                                |
      | "iosnownoqiwneoqiwoqiwjqpiwjeqiwenoiansoanoiqwnoiqwneoiqnwoiqneoiqnewoqwnqiwnanoaubsubwqouqorwhqwroiqwhoiqhoqheoqwnoaboaboqwbeoqwbeqobeoqebqowiqoiboabcoaboqbwoqbeoiqbeoqeboqobxzjowibakjsbkjasjkabqwueoqeubqowbowabfobaowqoweboqbweqbwoqbofboabobfoasqowbeoqbr@gmail.com" |
	
  Scenario: Login without password
    Given user is in the webpage "https://pilot.cloudmatos.dev/login"
    When user enters the "validaddress123@gmail.com" in Email field
    And user clears text in the password field
    And user clicks signin button
    Then user should get the "Please fill valid email or password."

  Scenario: Login without username
    Given user is in the webpage "https://pilot.cloudmatos.dev/login"
    When user enters the "validpass12" in password field
    And user clears text in the username field
    And user clicks signin button
    Then user should get the "Please fill valid email or password."

  Scenario: Verify remember me is clickable
    Given user is in the webpage "https://pilot.cloudmatos.dev/login"
    When user clicks the remember me check box
    Then user should be able to click it
