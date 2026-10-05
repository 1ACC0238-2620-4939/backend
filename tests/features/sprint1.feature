Feature: Persisted Sprint 1 operations
  # US23, US24
  Scenario: Vehicle registration survives a separate read
    Given a new test vehicle
    When its capacity is changed
    Then its identifier and capacity are persisted

  # US17, US19, US22
  Scenario: A scheduled trip can start and complete
    Given a new scheduled test trip
    When the trip starts and completes
    Then the completed trip rejects cancellation

  # US06
  Scenario: A missing trip returns a client error
    Given a trip identifier that does not exist
    When its details are requested
    Then the response is not found

  # US03, US04
  Scenario: Profile updates persist
    Given a new test profile
    When its full name is changed
    Then the updated full name can be read

  # US25, US26
  Scenario: Driver state changes persist
    Given a new test driver
    When the driver is deactivated and activated
    Then the driver is active with its profile reference
