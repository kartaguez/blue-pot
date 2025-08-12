# Application BluePot

## Goal and use cases
- Back end application for storing and manage shared expenses between people and get a snapshot of balances.
- Provides an API for web clients for CRUDing "pots" (group of people and their shared expenses) and "expenses" and getting balances.

## Overall architecture
- Core library with business objects and rules to manages expenses and balances
- Application library to implement the use cases: create a pot, add an expense, recalculate balances...
- Infrastructure : JPA repositories for storing business objects
- Applications: web API for CRUDing pots and expenses, web api for retrieving balances, worker for calculating balances (asynchronous calculation)

## Roadmap
- Finalize _core_ module:
 - Unit tests on business objects
- Have 1 use case work:
 - Unit tests
 - Integration tests

- Next:
 - Add post CRUD events for worker to trigger recalculation
 - Implement recalculation engine with actors framework (1 actor per pot)
 - Implement E2E test with :
  - pot creation
  - expense creation
  - balances retrieval
