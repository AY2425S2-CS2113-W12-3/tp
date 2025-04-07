package seedu.fintrack.utils;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.RecurringExpense;
import seedu.fintrack.Savings;
import seedu.fintrack.Categories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void saveExpensesToFile(ExpenseList expenseList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt"))) {
            for (Expense expense : expenseList.getExpenseList()) {
                writer.write(expense.getAmount() + "|" + expense.getCategory() + "|"
                        + expense.getDescription() + "|" + DATE_TIME_FORMAT.format(expense.getDate()) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses to file.");
        }
    }

    public void loadExpensesFromFile(ExpenseList expenseList) {
        File file = new File("expenses.txt");
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\|");
                // Skip malformed lines
                if (data.length < 4) {
                    continue;
                }

                int amount = Integer.parseInt(data[0]);
                String category = data[1];
                String description = data[2];
                Date date;
                
                try {
                    // Try parsing with the new format first
                    date = DATE_TIME_FORMAT.parse(data[3]);
                } catch (ParseException e) {
                    try {
                        // If that fails, try the old format
                        date = DATE_FORMAT.parse(data[3]);
                    } catch (ParseException e2) {
                        System.out.println("Error parsing date: " + data[3]);
                        continue;
                    }
                }

                Expense expense = new Expense(amount, category, description, date);
                expenseList.addExpense(expense);
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses from file.");
        }
    }

    public static void savRecurringExpensesToFile(ExpenseList expenseList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("recurring_expenses.txt"))) {
            for (RecurringExpense expense : expenseList.getRecurringExpenses()) {
                writer.write(expense.getAmount() + "|" + expense.getCategory() + "|"
                        + expense.getDescription() + "|" + expense.getFrequency() + "|"
                        + DATE_TIME_FORMAT.format(expense.getStartDate()) + "|"
                        +  DATE_TIME_FORMAT.format(expense.getLastProcessedDate()) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving recurring expenses to file.");
        }
    }

    public void loadRecurringExpensesFromFile(ExpenseList expenseList) {
        File file = new File("recurring_expenses.txt");
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\|");
                // Skip malformed lines
                if (data.length < 6) {
                    continue;
                }
                assert data.length == 6: "Length of data is 6";

                int amount = Integer.parseInt(data[0]);
                String category = data[1];
                String description = data[2];
                String frequency = data[3];
                Date startDate;
                Date lastProcessedDate;

                try {
                    // Try parsing with the new format first
                    startDate = DATE_TIME_FORMAT.parse(data[4]);

                } catch (ParseException e) {
                    try {
                        startDate = DATE_FORMAT.parse(data[4]);
                    } catch (ParseException e2) {
                        System.out.println("Error parsing date: " + data[4]);
                        continue;
                    }
                }

                try {
                    lastProcessedDate = DATE_TIME_FORMAT.parse(data[5]);
                } catch (ParseException e) {
                    try {
                        lastProcessedDate = DATE_FORMAT.parse(data[5]);
                    } catch (ParseException e2) {
                        System.out.println("Error parsing date: " + data[5]);
                        continue;
                    }
                }

                RecurringExpense expense = new RecurringExpense(amount, category, frequency,
                        description, startDate, startDate);
                expense.setLastProcessedDate(lastProcessedDate);
                expenseList.addRecurringExpense(expense);
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses from file.");
        }
    }

    public void loadCategoriesFromFile() {
        File file = new File("categories.txt");
        if (!file.exists()) {
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] categories = scanner.nextLine().split("\\|");
                for(String category: categories) {
                    Categories.addCategory(category);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading categories from file.");
        }
    }

    public static void saveCategoriesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categories.txt"))) {
            int firstCustomCategoryIndex = 6;
            for (int i = firstCustomCategoryIndex; i < Categories.size(); i++) {
                writer.write(Categories.getCategory(i+1) + "|");
            }
        } catch (IOException e) {
            System.out.println("Error saving categories to file.");
        }
    }

    public static void saveSavingsToFile(int income, int savingsGoal, int totalSavings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("savings.txt"))) {
            writer.write(income + "\n");
            writer.write(savingsGoal + "\n");
            writer.write(totalSavings + "\n");
        } catch (IOException e) {
            System.out.println("Error saving expenses to file.");
        }
    }

    public static Savings loadSavingsFromFile() {
        File file = new File("savings.txt");

        // If file doesn't exist, return a default Savings object
        if (!file.exists()) {
            return new Savings();  // Return a new default object
        }

        try (Scanner scanner = new Scanner(file)) {
            String[] data = new String[3];
            int count = 0;

            // Read all lines (assuming the file is properly formatted)
            while (scanner.hasNextLine() && count < 3) {
                data[count] = scanner.nextLine();
                count++;
            }

            if (count < 3) {
                System.out.println("Error: File format is incorrect.");
                return new Savings();  // Return a default object if the file format is invalid
            }

            // Parse the data
            int income = Integer.parseInt(data[0]);
            int savingsGoal = Integer.parseInt(data[1]);
            int totalSavings = Integer.parseInt(data[2]);

            // Return a new Savings object with the loaded data
            return new Savings(income, savingsGoal, totalSavings);

        } catch (IOException e) {
            System.out.println("Error loading savings from file.");
            return new Savings();  // Return a default object in case of error
        }
    }

}
