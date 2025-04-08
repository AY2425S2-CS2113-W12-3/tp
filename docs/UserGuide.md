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

**Caveat:** The maximum amount that can be entered is $1,000,000.00 (one million dollars). If you attempt to enter an amount exceeding this limit, the system will display an error message.

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

**Description:** Displays all expenses for the current month, the user's income status.

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
- **Financial insight:** FinTrack detects your spending habits and gives you simple feedback on them

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

**Caveat:** The maximum amount that can be entered is $1,000,000.00 (one million dollars). If you attempt to enter an amount exceeding this limit, the system will display an error message.

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
- Enter date: (optional) `2025-03-28`
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


### 6. Adding a Recurring Expense

**Command:** `recurring`

**Description:** Enables users to add expenses that occur regularly, automating the entry of predictable expenses such as subscriptions, rent, or allowances.

**Format:**
```
recurring
```

**Interaction:**
When you use the `recurring` command, FinTrack will guide you through a series of prompts to define your recurring expense:

1.  **Amount:** You will be asked to enter the expense amount in dollars and cents. (Note that the maximum input is $1 000 000.00 and cents has to be betwen 0 to 99 inclusive)
2.  **Category:** You will be prompted to specify the index of the particular category for the recurring expense. Choose from the listed defined categories.
3.  **Description:** Provide a brief description for the recurring expense (e.g., "Netflix subscription", "Weekly allowance").
4. **Frequency:** Specify how often the expense recurs. You can choose between `Weekly`, `Monthly`, or `Yearly`.


**Example:**
To add a monthly Netflix subscription of $15.99, enter:
```
recurring
```
Then follow the given prompts and type

```
15,99,3,Netflix,monthly
```
The output will be 
```
Recurring expense added for Entertainment: Netflix on 2025-04-08 00:14:27
```
---

### 7. Updating recurring expenses

**Command:** `update recurring`

**Description:** : Allows the user to update the recurring expense amount, frequency, description, and category


*Note: Updating recurring expense will set the start date of the recurring expense to be the current date, so if you originally had an expense that resets monthly, and you update the frequency to weekly, it will be added into the expense list 1 week after the date you updated the recurring expense.

**Format:**
```
update recurring
```

**Interaction:**
When you use the `update recurring` command, FinTrack will show you your list of recurring expenses,
and ask you to choose the index of the recurring expense you want to update and show the update prompt. The update prompt is similar to the prompt shown when adding recurring expense. If you decide not to update any recurring expenses, follow the prompt and type 0.


**Example:**
```
update recurring
```
Then follow the prompts, type the index of the recurring expense you want to update and enter, then update the expense

```
9,99,3,Spotify,monthly
```
The output will be
```
Recurring Expense updated
```


---

### 8. Viewing list of recurring expenses

**Command:** `view recurring`

**Description:** : shows the user the list of recurring expenses

**Format:**
```
view recurring
```

**Interaction:**
When you use the `view recurring` command, FinTrack will show you your list of recurring expenses.

**Example:**
```
view recurring
```

The output will be
```
These are your current recurring expenses:
1. Spotify: $9.99, monthly
```
If there is no recurring expenses, calling `view recurring ` will output

```
There are currently no recurring expenses
```

---

### 9. Deleting recurring expenses

**Command:** `delete recurring`

**Description:** Enables users to delete recurring expenses, thus the program will not automate the entry of the particular recurring expense anymore.

*Note: The expenses of recurring expense that is added, before the recurring expense is removed, will not be removed, and can be viewed via the `history` command. To delete an expense added due to recurring expense, follow the normal `delete` commands.

**Format:**
```
delete recurring
```

**Interaction:**
When you use the `delete recurring` command, FinTrack will show you your list of recurring expenses, and ask for the index of the recurring expense you want to delete

**Example:**
To delete the Netflix subscription of $15.99, enter:
```
delete recurring
```
Then follow the displayed messages and type the recurring expense index.
The output will be
```
Recurring Expense deleted: Entertainment: Spotify, monthly
```

---



### 10. Managing Expense Categories

#### Add a New Category

**Command:** `category add`

**Description:**  Allows users to add new custom categories for better expense tracking and categorization.

**Format:**
```
category add
```
Enter the name/names of the new category when prompted.

**Example:**
To add a new category named 'Healthcare':
```
category add
```
Then enter: `Healthcare` or `Healthcare,Subscriptions,Work`

#### Delete a Category

**Command:** `category del`

**Description:**  Enables users to remove **custom** categories that are no longer needed.

**Format:**
```
category del
```

**Interaction:**
- When you enter the `category del` command, FinTrack will first display the current list of categories with their corresponding index numbers.
- You will then be prompted to enter the index/indexes of the category you wish to delete.
- This command only allows you to delete **custom** categories, as the first 5 categories are default and should not be removed.

**Example:**
```
category del
```
*Followed by the index/indexes of the category to be deleted when prompted.*

Input: `1` or `1,2,3`

**NOTE:** Expenses with a deleted custom category tagged to them will retain their categories, even if they are deleted.

---
### 11. Updating Income

**Command:** `update income`

**Description:**  Allows users to modify their monthly income in the application. Users must input the income in dollars only to ensure accuracy.

**Format:**
```
update income
```
**Interaction:**
- When you enter the update income command, FinTrack will first display the current income.
- You will be prompted to enter the new dollar amount of your income.
- If the input is valid, FinTrack will update the income and save the changes.

**Example:**
```
update income
```
*Followed by the <dollars> when prompted.*

Input: `1000` 

**NOTE:** The user starts off with income of $1000 and no current savings. Monthly budget is income - savings goals.

### 12. Updating Savings Goals

**Command:** `update savings goals`

**Description:**  Allows users to modify their monthly savings goal in the application. Users must input the savings goal in both dollars and cents to ensure accuracy. The savings goal must be greater than zero and less than the monthly income.

**Format:**
```
update savings goal
```
**Interaction:**
- When you enter the update savings goal command, FinTrack will first display the current savings goal.
- You will be prompted to enter the new dollar amount of your savings goal.
- You will then be prompted to enter the cents amount separately.
- If the input is valid, FinTrack will update the savings goal and save the changes.

**Example:**
```
update savings goals
```
*Followed by the <dollars> and then <cents> when prompted.*

Input: `10` then `20`

**NOTE:** The user starts off with savings goals of $100 and no current savings.

---
### 13. Tips

**Command:** `tips`

**Description:** Randomly generates a financial tip from a pool of tips.  

**Format:**
```
tips
```
**Interaction:**
- When you enter the tipsd, FinTrack will first generate random number.
- And it will produce a finance tip from that index.

