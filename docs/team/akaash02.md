# akaash02 - Project Portfolio Page

## Overview
FinTrack is a CLI expense tracking application that helps users manage their daily expenses. The application allows users to track, categorize, and manage their spending while maintaining a simple and efficient interface.

### Summary of Contributions
**Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=akaash02&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~test-code~other~functional-code)

[//]: # (**Enhancements to existing features**:)


**Set up basic classes:**

* Expense class:
* ExpenseList class
* FinTrack class

**Created Storage functionality**

* Developed the storage system to ensure that user data, such as expenses and savings, is persisted across sessions, allowing users to close and reopen the application without losing any of their financial information.

* Storage functionality for expenses: The storage system was designed to track and save user expenses in a structured way. This ensures that every time an expense is added, modified, or deleted, the changes are saved to the storage and accurately reflected when the application is restarted. Users are able to keep a comprehensive record of their expenses, sorted and accessible as needed.

* Storage functionality for savings: Another critical aspect of the storage system was the management of savings data. I created a method to store and retrieve users’ savings, as well as the income and savings goals they set. This allows users to manage their savings progress over time, ensuring that any changes they make to their income or savings goals are reflected in the storage system and accessible across sessions.

Template for future expansions: One of the key features of this storage system is its extensibility. By developing this as a generalized storage template, other classes within the application can now leverage this functionality to store and retrieve data. This was crucial as it streamlined future development by making it easy to integrate new features that require persistent data storage.

**Created Savings functionality**

* Savings tracking: A major enhancement was the addition of features that allow users to set a monthly savings goal, track their income, and monitor their progress towards achieving their savings goal. This functionality enables users to manage their finances more effectively by having a clear picture of how much they should be saving each month and whether they are on track to meet their financial targets.

* Integrated with other functionalities: The savings functionality was not developed in isolation but was tightly integrated with the rest of the application. For example, when a user updates their income, the system automatically adjusts the savings goal accordingly. Similarly, if users add new expenses, their ability to meet their savings goals can be reevaluated. This ensures a cohesive financial management experience.

* Interfacing with storage: The savings system was also integrated with the storage module to ensure that savings goals and income values are saved to and retrieved from the storage. This integration allows the application to track changes to a user’s savings goals and income over time. This functionality is particularly useful for long-term financial planning as users can set their goals once and see how they evolve based on their financial activities, which are also stored in the application.

* Income and savings goal validation: I implemented various checks to ensure the integrity of the savings goal and income values. For instance, the system ensures that the savings goal is always less than or equal to the user’s income, preventing users from setting an unrealistic savings target that exceeds their available income.

**Created test cases**

* JUnit tests for Expense and ExpenseList classes: I designed and implemented a comprehensive suite of JUnit test cases to ensure that all operations related to expenses were functioning correctly. This includes tests for adding, deleting, and modifying expenses in the expense list, as well as ensuring the accuracy of any associated calculations such as totals and categorization. The tests help to confirm that the system is resilient to edge cases and that any bugs related to expense management are caught early in the development process.

* JUnit tests for the Savings class: A significant portion of the application’s functionality revolves around managing savings, income, and savings goals. To ensure this system works flawlessly, I wrote JUnit tests to validate all critical features of the Savings class. This includes tests for setting savings goals, updating income, calculating the required monthly savings, and ensuring the application reacts correctly to edge cases such as setting a goal higher than the income or entering invalid values. These tests ensure that the savings logic is robust and that the system responds appropriately to user input.

* JUnit tests for the Ui class: The User Interface (UI) is the face of the application, so I wrote JUnit tests to ensure that all interactions between the user and the system are working as expected. These tests validate that the appropriate messages are displayed when users interact with the system, whether they're entering new expenses, updating savings goals, or encountering an error. The tests also verify that the UI handles edge cases and invalid inputs gracefully, providing users with helpful feedback when things go wrong.

* I/O testing: One of the more complex parts of the application was ensuring that the system handles input and output (I/O) correctly, particularly when interacting with files for storage. To ensure that the application behaves as expected in various scenarios, I expanded the testing suite to cover I/O scenarios, including saving and loading data from the file, handling corrupted files, and verifying that data is correctly persisted and retrieved. These tests ensure that the application is able to handle file operations reliably without data loss or corruption, even if users encounter issues like incorrect input or application crashes.

* Edge case testing: Throughout the testing process, I made sure to test various edge cases, such as entering negative values, very large amounts, or invalid input formats. These tests ensure that the application is robust and user-proof, and that the system responds appropriately to unexpected or erroneous inputs. This comprehensive testing approach ensures that the application will work smoothly for a wide range of user scenarios, including those outside the typical expected flow.

