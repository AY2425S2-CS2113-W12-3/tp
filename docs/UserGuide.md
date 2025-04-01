# FinTrack User Guide

<!-- @@author kaxiangg -->

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

**Description:** Adds a new expense entry to the tracking list.

**Format:** After typing `add`, press Enter and enter the expense details in the format:

```
DOLLARS, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE (YYYY-MM-DD)
```

- The `DOLLARS` and `CENTS` should be non-negative integers.
- The `CATEGORY_INDEX` corresponds to a predefined category:

| Index | Category      |
|-------|---------------|
| 1     | Food          |
| 2     | Transport     |
| 3     | Entertainment |
| 4     | Utilities     |
| 5     | Household     |
| 6     | Others        |

- `DESCRIPTION` is an optional text describing the expense.
- `DATE` must be in `YYYY-MM-DD` format.

**Example:**

```
add 12, 50, 1, Lunch, 2025-03-28
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

**Description:** Modifies details of an existing expense.

**Format:** After typing `update`, press Enter and follow the instructions to enter the expense details:

```
INDEX, DOLLARS, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE (YYYY-MM-DD)
```

- `INDEX` is the expense’s position in the history list.
- `DOLLARS`, `CENTS`, `CATEGORY_INDEX`, `DESCRIPTION`, and `DATE` follow the same rules as in the `add` command.

**Example:**

```
update 3, 15, 00, 2, Grab Taxi, 2025-03-28
```

---

### 5. Deleting an Expense

**Command:** `delete`

**Description:** Removes an expense from the list.

**Format:**

```
delete INDEX
```

- `INDEX` is the number shown in the history list.

**Example:**

```
delete 1
```

---
<!-- @@author -->

### 6. Setting a Monthly Budget

**Command:** `budget`

**Description:** Sets a spending limit for the month.

**Format:**

```
budget
```

- Follow the prompt to enter the budget amount.

---

### 7. Adding a Recurring Expense

**Command:** `recurring`

**Description:** Adds an expense that repeats on a regular basis.

**Format:** Follow the prompt to enter:

- Amount
- Category
- Frequency (`Weekly`, `Monthly`, or `Yearly`)
- Description
- Start date (`YYYY-MM-DD` format)

---

### 8. Managing Expense Categories

#### Add a New Category

**Command:** `category add`

**Description:** Adds a new category to the list.

**Format:**

```
category add CATEGORY_NAME
```

**Example:**

```
category add Healthcare
```

#### Delete a Category

**Command:** `category del`

**Description:** Removes a category from the list.

**Format:**

```
category del CATEGORY_NAME
```

**Example:**

```
category del Healthcare
```

---

### 9. Updating Income

**Command:** `update income`

**Description:** Updates the user's recorded monthly income.

**Format:**

```
update income
```

- Follow the prompt to enter a new income value.

---

### 10. Updating Savings Goal

**Command:** `update savings goal`

**Description:** Updates the user's target savings amount for the month.

**Format:**

```
update savings goal
```

- Follow the prompt to enter a new savings goal.

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

