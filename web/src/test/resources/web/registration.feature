Feature: Bank transfer.

  Scenario: Money should be transferred from applicant account to beneficiary account.
    Given Account "001" has balance of "$100".
    And Account "002" has balance of "$1000".
    When Amount of "$50" is transferred from account "001" to account "002".
    Then Account "001" should have balance of "$50".
    And Account "002" should have balance of "$1050".
