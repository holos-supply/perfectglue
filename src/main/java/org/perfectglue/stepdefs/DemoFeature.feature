# Unnecessary file here only for the example DemoStepDefinitions
Feature: My candy
    Scenario: I get more candy
    
    Given I had 3 bags of candy
    When I am given 1 bags of candy from Jack
    Then I have 4 bags of candy
    
    
    Scenario: I eat candy
    
    Given I have 6 bags of candy
    When I eat 5 bags of candy 
    Then I have 1 bags of candy
    And my situation is:
    | stomach.status | hurts |
    
    
    
    
