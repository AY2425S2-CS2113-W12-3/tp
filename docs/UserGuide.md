# FinTrack User Guide

## Introduction

FinTrack is a personal finance tracking application targeted at young adults
that helps you manage your daily expenses through
a command-line interface. It allows you to add, view, update, and delete expenses easily.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `Fintrack`
   from [here](https://github.com/AY2425S2-CS2113-W12-3/tp/releases/download/A-Jar/tp.jar).
3. Copy the downloaded file to a folder you want to use as the home folder for FinTrack.
4. Open a command prompt and navigate to the folder.
5. Run the command: `java -jar fintrack.jar`

## Features

### Adding an expense: `add`

Adds a new expense to the tracking list.

Format: `add`, press enter and
enter expense details in the format: `DOLLAR, CENTS, CATEGORY_INDEX, DESCRIPTION, DATE`

* The `DOLLAR` and `CENTS` should be non-negative integers
* The `CATEGORY` must be one of: Food, Transport, Entertainment, Utilities, Household, Others.
* The `CATEGORY_INDEX` is a number from 1 to 6 corresponding to the category,
  which includes:

| Index | Category      |
 |-------|---------------|
| 1     | Food          |
| 2     | Transport     |
| 3     | Entertainment |
| 4     | Utilities     |
| 5     | Household     |
| 6     | Others        | 

Optionally, you can add categories using the command `category add <category_name>` and delete categories `category del
 <category_name>` to customize the categories to your preference and the commands will be explained later on.

* The `DESCRIPTION` is an optional text describing the expense
* The `DATE` should be in YYYY-MM-DD format

Example:

* `add 12, 50, 1, Lunch 2025-03-28`

### Viewing expense history: `history`

Shows a list of all recorded expenses.

Format: `history`

### Updating an expense: `update`

Updates an existing expense in the list.

Format: `update`,
press enter
expense details in the format: `INDEX, AMOUNT, CATEGORY_INDEX, DESCRIPTION, DATE`

* The `INDEX` refers to the expense's position in the list (visible when using `history`)
* The `AMOUNT` should be a non-negative number
* The `CATEGORY_INDEX` is a number from 1 to 6 corresponding to the category,
  which includes: 1 - Food, 2 - Transport, 3 - Entertainment, 4 - Utilities, 5 - Household, 6 - Others.
* The `DESCRIPTION` is an optional text describing the expense
* The `DATE` should be in YYYY-MM-DD format
* Example: `update` `10, 00 , 2, Grab taxi 2025-03-28`

### Deleting an expense: `delete`

Removes an expense from the list.

Format: `delete INDEX`

Example:

* `delete 1`

## Command Summary

* Add expense: `add`
* View history: `history`
* Update expense: `update`
* Delete expense: `delete`