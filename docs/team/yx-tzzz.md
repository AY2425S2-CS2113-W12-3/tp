# yx-tzzz - Project Portfolio Page

## Overview
FinTrack is a CLI expense tracking application that helps users manage their daily expenses. The application allows users to track, categorize, and manage their spending while maintaining a simple and efficient interface.

### New feature: Added recurring expenses and its commands
* What it does: allows FinTrack to automatically add an expense depending on the given frequency by the user. The feature consists of add recurring expense, update recurring expense, delete recurring expense and view recurring expense
* Justification: This feature improves the product significantly as the user does not have to manually key in expenses that occur at a set frequency
* Highlights: The expenses of a recurring expense is added simply by running the program

### Summary of Contributions
**Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=yx-tzzz&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false);

**Set up basic classes:**

* Recurring Expense class to implement recurring expenses
* AddRecurringExpenseCommandTest, DeleteRecurringExpenseCommandTest, ViewRecurringExpensesCommandTest and UpdateRecurringExpenseCommandTest classes for JUnit tests

**Set up Recurring Expense Command classes:**

* AddRecurringExpenseCommand, DeleteRecurringExpenseCommand, ViewRecurringExpensesCommand and UpdateRecurringExpenseCommand classes


**Created Recurring Expense functionality**

* Added functions to add recurring expense, update recurring expense, delete recurring expense, and view recurring expenses
* Integrated it with the rest of the commands to function as one

**Enhancements to existing features**
* Storage functionality for recurring expenses


**Created test cases**

* JUnit tests for Recurring Expense class
* JUnit tests for methods using RecurringExpense in ExpenseList class
* JUnit tests for AddRecurringExpenseCommand, DeleteRecurringExpenseCommand, ViewRecurringExpensesCommand classes

**Documentation**
* User Guide
    * Added documentation for the commands add recurring expense, delete recurring expense, update recurring expense, view recurring expenses.

* Developer Guide
    * Added implementation details for recurring expense feature