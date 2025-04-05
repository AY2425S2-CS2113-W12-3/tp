# FinTrack User Guide

<!-- @@author kaixiangg -->

## Introduction

FinTrack is a personal finance tracking application designed to help users efficiently manage their expenses through a
command-line interface. Users can add, view, update, and delete expenses, set budgets, track savings goals, and
categorize expenses.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `Fintrack`
   from [here](https://github.com/AY2425S2-CS2113-W12-3/tp/releases).
3. Copy the downloaded file to a folder you want to use as the home folder for FinTrack.
4. Open a command prompt and navigate to the folder.
5. Run the command: `java -jar fintrack.jar`

## Features

### 1. Adding an Expense

**Command:** `add`

**Description:**  Allows users to input and record a new expense into the FinTrack system.

**Format:**
```
add DOLLARS, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE
```
or
```
add
DOLLARS, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE
```
You can input all parameters in one line or in separate lines after typing `add` and pressing Enter.

**Parameters:**

-   **Amount**:
    -   `DOLLARS`: A non-negative integer representing the dollar amount.
    -   `CENTS`: A non-negative integer less than 100, representing the cent amount.
    -   Use a comma to separate dollars and cents (e.g., `12,50` for $12.50).
-   **Category Index**:
    -   `CATEGORY_INDEX`: An integer corresponding to the index of the category in the list of categories.
    -   Use the `help` command to view the category index. The categories are:

        | Index | Category      |
        |-------|---------------|
        | 1     | Food          |
        | 2     | Transport     |
        | 3     | Entertainment |
        | 4     | Utilities     |
        | 5     | Household     |
        | 6     | Others        |
-   **Description**:
    -   `DESCRIPTION`: An optional text field to describe the expense.
    -   If the description contains spaces, enclose it in double quotes (e.g., `"Dinner with friends"`).
-   **Date**:
    -   `DATE`: The date the expense was incurred, in `YYYY-MM-DD` format.

**Functionality:**
-   **Input Parsing**: Parses the input string to extract the amount, category index, description, and date.
-   **Validation**:
    -   **Amount**: Checks if dollars and cents are non-negative integers.
    -   **Category Index**: Validates if the category index is within the range of defined categories.
    -   **Date**: Verifies if the date is in the `YYYY-MM-DD` format and is a valid date.
-   **Error Handling**: If any validation fails, an error message is displayed, and the expense is not added.
-   **Expense Creation**: If all inputs are valid, an `Expense` object is created with the provided details and added to the expense list.

**Example:**
To add an expense of $12.50 for lunch in the 'Food' category on March 28, 2025:
```
add 12,50, 1, Lunch, 2025-03-28
```
or, input parameters in separate lines:
```
add
12,50, 1, Lunch, 2025-03-28
```

---

### 2. Viewing Monthly Expenses

**Command:** `viewmonth`

**Description:** Displays all expenses for the current month, the user's income, and budget status.

**Format:**

```
viewmonth
```

---

### 3. Viewing Spending History

**Command:** `history`

**Description:** Displays all past expenses with categories and dates.

**Format:**

```

history
```

---

### 4. Updating an Expense

**Command:** `update`

**Description:**  Allows users to modify the details of an already recorded expense in the FinTrack system.

**Format:**
```
update INDEX, DOLLARS, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE
```
or
```
update
INDEX, DOLLARS, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE
```
You can input all parameters in one line or in separate lines after typing `update` and pressing Enter.

**Parameters:**

-   **Index**:
    -   `INDEX`: The position number of the expense you wish to update.
    -   To find the index, use the `history` command to view all recorded expenses and their corresponding index numbers.
-   **Amount**:
    -   `DOLLARS`: A non-negative integer for the dollar amount.
    -   `CENTS`: A non-negative integer less than 100 for the cent amount.
    -   Separate dollars and cents with a comma (e.g., `15,00` for $15.00).
-   **Category Index**:
    -   `CATEGORY_INDEX`: An integer from 1 to 6 representing the expense category.
    -   Refer to the `help` command output for the list of categories and their indices.
-   **Description**:
    -   `DESCRIPTION`:  The new description for the expense (optional).
    -   Enclose descriptions with spaces in double quotes (e.g., `"Grab ride"`).
-   **Date**:
    -   `DATE`: The updated date of the expense in `YYYY-MM-DD` format.

**Functionality:**
-   **Expense Identification**: Uses the provided `INDEX` to locate the expense to be updated in the expense list.
-   **Input Parsing and Validation**:
    -   Parses the input for amount, category index, description, and date, similar to the `add` command.
    -   Validates all input parameters to ensure they meet the required format and criteria.
-   **Error Handling**:
    -   If the provided `INDEX` is invalid (e.g., out of range), an error message is displayed.
    -   If any of the updated parameters are invalid, an error message is shown, and the expense is not updated.
-   **Expense Update**: If all inputs are valid and the expense index exists, the details of the expense at the specified index are updated with the new information.

**Example:**
To update the 3rd expense in the list to $15.00 for 'Grab Taxi' under the 'Transport' category on March 28, 2025:
```
update 3, 15, 00, 2, Grab Taxi, 2025-03-28
```
or, input parameters in separate lines:
```
update
3, 15, 00, 2, Grab Taxi, 2025-03-28

```

---

### 2. Viewing Monthly Expenses

**Command:** `viewmonth`

**Description:** Displays all expenses for the current month, the user's income, and budget status.

**Format:**

```
viewmonth
```

---

### 3. Viewing Spending History

**Command:** `history`

**Description:** Displays all past expenses with categories and dates.

**Format:**

```
history
```

---


### 2. Viewing Monthly Expenses

**Command:** `viewmonth`

**Description:** Displays a summary of expenses for the current month.

**Format:**
```
viewmonth
```

**Output:**
- **Current Month:** Shows the month and year for the displayed expenses.
- **Your current income:** Displays the user's set monthly income.
- **Budget Status:**
  - If monthly expenses are less than the savings goal, it indicates the amount still available to spend to meet the savings goal.
  - If expenses exceed the savings goal, it shows the amount by which the goal has been exceeded.
- **Monthly Expenses List:** A list of expenses for the current month, including:
  - Index number for each expense.
  - Description of the expense.
  - Amount spent in cents.

**Example:**
```
viewmonth
```

---

### 3. Viewing Spending History

**Command:** `history`

**Description:** Shows a list of all recorded expenses.

**Format:**
```

history
```

**Output:**
- **Spending history:** A numbered list of all expenses, including:
  - Index number.
  - Description of the expense.
  - Amount spent in cents.
  - Date of the expense.
  - Category of the expense.

**Example:**
```
history

```

---

### 5. Deleting an Expense

**Command:** `delete`

**Description:** Removes a specific expense from the expense history.

**Format:**
```
delete INDEX
```

**Parameter:**
- `INDEX`: The number of the expense to delete, as listed in the spending history (viewable with the `history` command).

**Important:**
- Ensure you have viewed the history to identify the correct index of the expense you wish to delete.

**Example:**
To delete the first expense listed in the history:
```
delete 1
```

---
<!-- @@author -->

### 6. Setting and updating Monthly Budget

**Command:** `budget`


**Description:** Allows users to set a monthly spending budget to manage their expenses against a predefined limit.
The format to update the budget is the same.


**Format:**
```
budget
```

**Interaction:**
- Upon entering the `budget` command, FinTrack will prompt you to input your desired monthly budget.
- Enter the budget amount as a positive integer. This budget is used to track your spending and will be displayed in the `viewmonth` command.

**Functionality:**
- **Budget Setting**: Sets the monthly budget limit.
- **Budget Tracking**: Calculates and displays the remaining budget based on expenses added. The remaining budget is updated each time an expense is added using the `add` command.
- **Integration with `viewmonth`**: The set budget and remaining budget are displayed when using the `viewmonth` command, providing a clear view of your budget status for the month.

**Example:**
```
budget
```
*Followed by user input of the budget amount when prompted.*

---

### 7. Adding a Recurring Expense (*Coming soon*)

**Command:** `recurring`

**Description:** Enables users to add expenses that occur regularly, automating the entry of predictable expenses such as subscriptions, rent, or allowances.

**Format:**
```
recurring
```


**Interaction:**
When you use the `recurring` command, FinTrack will guide you through a series of prompts to define your recurring expense:


1.  **Amount:** You will be asked to enter the expense amount in cents. Please enter a non-negative integer.
2.  **Category:** You will be prompted to specify the category for the recurring expense. Choose from the predefined categories (viewable using `help`).
3.  **Frequency:** Specify how often the expense recurs. You can choose between `Weekly`, `Monthly`, or `Yearly`. Type in the frequency and press Enter.
4.  **Description:** Provide a brief description for the recurring expense (e.g., "Netflix subscription", "Weekly allowance").
5.  **Start date:** Enter the date when the recurring expense begins in `YYYY-MM-DD` format.

After you provide all the details, FinTrack automatically calculates and adds all past occurrences of the recurring expense up to the current date into your expense list. This ensures that your expense history is complete from the start date of the recurring expense.

**Functionality:**
- **Recurring Expense Setup**: Defines a recurring expense with amount, category, frequency, description, and start date.
- **Frequency Options**: Supports `Weekly`, `Monthly`, and `Yearly` frequencies.
- **Automatic Expense Generation**: Automatically generates and adds past expenses to the expense list from the start date to the current date, based on the specified frequency.
- **Savings Update**: Updates the total savings by considering the amount of the recurring expense.

**Example:**
To add a monthly Netflix subscription of $15.99 starting from April 5, 2025:
```
recurring
```
*Follow the prompts to enter amount, category, frequency (Monthly), description (Netflix), and start date (2025-04-05).*

**Example:**
```
//coming soon
```
---

### 8. Managing Expense Categories

#### Add a New Category

**Command:** `category add`

**Description:**  Allows users to add new categories for better expense tracking and categorization.

**Format:**
```
category add CATEGORY_NAME
```

**Parameter:**
- `CATEGORY_NAME`: The name of the new category you wish to add. Category names should be single words.

**Functionality:**
- Checks if the category name is empty. If it is, an error message is displayed.
- Checks if the category already exists. If it does, an error message is shown.
- If the category name is valid and does not already exist, it is added to the list of categories.
- The updated category list is saved.

**Example:**
To add a new category named 'Healthcare':
```
category add Healthcare
```

#### Delete a Category

**Command:** `category del`

**Description:**  Enables users to remove categories that are no longer needed.

**Format:**
```
category del
```

**Interaction:**
- When you enter the `category del` command, FinTrack will first display the current list of categories with their corresponding index numbers.
- You will then be prompted to enter the index of the category you wish to delete.
- After entering the index, the category at that index will be removed.
- The updated category list is saved.

**Important:**
- Ensure you view the category list to choose the correct index for deletion.

**Example:**
```
category del
```
*Followed by the index of the category to be deleted when prompted.*

---

### 9. Updating Income

**Command:** `update income`

**Description:**  Allows users to modify their current monthly income in the application.

**Format:**
```
update income
```

**Interaction:**
- Upon entering `update income`, the application will first display your current recorded income.
- You will then be prompted to enter your new monthly income.
- Enter the new income value as a positive integer. If the input is invalid, the income will not be updated.

**Example:**
```
update income
```
*Followed by user input of the new income amount when prompted.*

---

### 10. Updating Savings Goal

**Command:** `update savings goal`

**Description:**  Enables users to set or adjust their monthly savings goal.

**Format:**
```
update savings goal
```

**Interaction:**
- When you use the `update savings goal` command, FinTrack will display your current savings goal.
- You will be prompted to input your new desired savings goal for the month.
- Enter the new savings goal as a positive integer. If the input is invalid, the savings goal will not be updated.

**Example:**
```
update savings goal
```
*Followed by user input of the new savings goal when prompted.*

---

### 11. Exiting the Program

**Command:** `exit`

**Description:** Saves all data and exits the application.

**Format:**

```
exit
```

---

### 12. Getting Help

**Command:** `help`

**Description:** Displays a list of all available commands and their usage.

**Format:**

```
help
```

## Command Summary

| Command               | Description                          |
|-----------------------|--------------------------------------|
| `add`                 | Adds a new expense                   |
| `viewmonth`           | Views expenses for the current month |
| `history`             | Shows spending history               |
| `update`              | Updates an existing expense          |
| `delete`              | Deletes an expense                   |
| `budget`              | Sets a monthly budget                |
| `recurring`           | Adds a recurring expense             |
| `category add`        | Adds a new category                  |
| `category del`        | Deletes a category                   |
| `update income`       | Updates the user's income            |
| `update savings goal` | Updates the savings goal             |
| `exit`                | Exits the application                |
| `help`                | Displays help information            |
