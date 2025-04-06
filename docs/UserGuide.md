# FinTrack User Guide

<!-- @@author kaixiangg -->

## Introduction

FinTrack is a personal finance tracking application designed to help users efficiently manage their expenses through a
command-line interface. Users can add, view, update, and delete expenses, set budgets, track savings goals, and
categorize expenses.

## Quick Start

1. Ensure that you have Java 11 or above installed.
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
add
```
Follow the prompts to enter:
- Amount (in dollars and cents)
- Category (select from available categories)
- Description
- Date (optional, in yyyy-MM-dd format)
- Time (optional, in HH:mm:ss format)

**Example:**
To add an expense of $12.50 for lunch in the 'Food' category on March 28, 2025:
```
add
```
Then follow the prompts:
- Enter dollars: `12`
- Enter cents: `50`
- Enter category index: `1` (Food)
- Enter description: `Lunch`
- Enter date: `2025-03-28`
- Enter time (optional): `15:30:00`

---

### 2. Viewing Monthly Expenses

**Command:** `viewmonth`

**Description:** Displays all expenses for the current month, the user's income, and budget status.

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
  - Amount spent in dollars and cents.

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
  - Amount spent in dollars and cents.
  - Date of the expense.
  - Category of the expense.

---

### 4. Updating an Expense

**Command:** `update`

**Description:**  Allows users to modify the details of an already recorded expense in the FinTrack system.

**Format:**
```
update
```
Follow the prompts to:
1. Select the expense to update (by index)
2. Enter new amount, category, description, and date

**Example:**
To update the 3rd expense in the list to $15.00 for 'Grab Taxi' under the 'Transport' category on March 28, 2025:
```
update
```
Then follow the prompts:
- Enter expense number to update: `3`
- Enter dollars: `15`
- Enter cents: `00`
- Enter category index: `2` (Transport)
- Enter description: `Grab Taxi`
- Enter date: `2025-03-28`
- Enter time (optional): `15:30:00`

---

### 5. Deleting an Expense

**Command:** `delete`

**Description:** Removes a specific expense from the expense history.

**Format:**
```
delete
```
Enter the index of the expense you want to delete when prompted.

**Important:**
- Ensure you have viewed the history to identify the correct index of the expense you wish to delete.

**Example:**
To delete the first expense listed in the history:
```
delete
```
Then enter: `1`

---

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
- Enter the budget amount in cents. This budget is used to track your spending and will be displayed in the `viewmonth` command.

**Example:**
```
budget
```
*Followed by user input of the budget amount when prompted.*
- Enter budget amount in cents: `100000` (for a $1000 monthly budget)

---

### 7. Adding a Recurring Expense

**Command:** `recurring`

**Description:** Enables users to add expenses that occur regularly, automating the entry of predictable expenses such as subscriptions, rent, or allowances.

**Format:**
```
recurring
```

**Interaction:**
When you use the `recurring` command, FinTrack will guide you through a series of prompts to define your recurring expense:

1.  **Amount:** You will be asked to enter the expense amount in dollars and cents.
2.  **Category:** You will be prompted to specify the category for the recurring expense. Choose from the predefined categories.
3.  **Frequency:** Specify how often the expense recurs. You can choose between `Weekly`, `Monthly`, or `Yearly`.
4.  **Description:** Provide a brief description for the recurring expense (e.g., "Netflix subscription", "Weekly allowance").
5.  **Start date:** Enter the date when the recurring expense begins in `YYYY-MM-DD` format.

**Example:**
To add a monthly Netflix subscription of $15.99 starting from April 5, 2025:
```
recurring
```
*Follow the prompts to enter amount, category, frequency (Monthly), description (Netflix), and start date (2025-04-05).*

---

### 8. Managing Expense Categories

#### Add a New Category

**Command:** `category add`

**Description:**  Allows users to add new categories for better expense tracking and categorization.

**Format:**
```
category add
```
Enter the name of the new category when prompted.

**Example:**
To add a new category named 'Healthcare':
```
category add
```
Then enter: `Healthcare`

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
- Enter the new income value in cents. If the input is invalid, the income will not be updated.

**Example:**
```
update income
```
*Followed by user input of the new income amount when prompted.*
- Enter new income amount in cents: `300000` (for a $3000 monthly income)

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
- Enter the new savings goal in cents. If the input is invalid, the savings goal will not be updated.

**Example:**
```
update savings goal
```
*Followed by user input of the new savings goal when prompted.*
- Enter new savings goal in cents: `50000` (for a $500 savings goal)

---

### 11. Clearing History

**Command:** `clear`

**Description:** Removes all expenses from your history.

**Format:**
```
clear
```

**Interaction:**
- When you enter the `clear` command, FinTrack will display a warning message.
- You will be prompted to confirm the deletion by typing 'yes'.
- If you confirm, all expenses will be deleted from your history.

**Example:**
```
clear
```
*Followed by typing 'yes' to confirm the deletion.*

---

### 12. Exporting Expenses

**Command:** `export`

**Description:** Exports your expenses to a CSV file.

**Format:**
```
export
```

**Interaction:**
- When you enter the `export` command, FinTrack will create a CSV file with all your expenses.
- The file will be saved in the 'exports' folder with a timestamp in the filename.

**Example:**
```
export
```
*The expenses will be exported to a CSV file in the 'exports' folder.*

---

### 13. Getting Help

**Command:** `help`

**Description:** Displays a list of all available commands and their usage.

**Format:**
```
help
```

**Output:**
- Detailed instructions for all commands, including:
  - Step-by-step guidance
  - Examples
  - Format requirements

---

### 14. Exiting the Program

**Command:** `exit`

**Description:** Saves all data and exits the application.

**Format:**
```
exit
```

---

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
| `clear`               | Clears all expenses                  |
| `export`              | Exports expenses to CSV              |
| `help`                | Displays help information            |
| `exit`                | Exits the application                |

## Tips for Using FinTrack

1. Use the `help` command whenever you're unsure about how to use a feature
2. Keep your categories organized to better track your spending patterns
3. Export your data regularly to maintain a backup of your expense history
4. Use descriptive names when adding expenses to make them easier to find later
5. Review your expense history regularly to stay on top of your spending

## Troubleshooting

If you encounter any issues:

1. Make sure you're using Java 11 or above
2. Check that your input follows the correct format
3. Use the `help` command to verify the correct usage
4. If the application becomes unresponsive, use `exit` to close it properly

## Saving Data

FinTrack data are saved in the hard disk automatically after any command that changes the data. You can find the data file in the same folder as the application.

## FAQ

**Q: How do I transfer my data to another Computer?**  
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the copy of your data from your previous FinTrack home folder.

**Q: What happens if I enter an invalid command?**  
A: The application will show an error message explaining what went wrong. Use the `help` command to see the list of valid commands.

**Q: Can I edit an expense after adding it?**  
A: Yes, use the `update` command to modify any existing expense.

**Q: How do I delete all my expenses?**  
A: Use the `clear` command and confirm with 'yes' when prompted.

**Q: Where are my exported files saved?**  
A: Exported files are saved in an 'exports' folder in the same directory as the application.
