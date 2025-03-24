package seedu.fintrack.utils;

import seedu.fintrack.Categories;
import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void saveExpensesToFile(ExpenseList expenseList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt"))) {
            for (Expense expense : expenseList.getExpenseList()) {
                writer.write(expense.getAmount() + "|" + expense.getCategory() + "|"
                        + expense.getDescription() + "|" + DATE_FORMAT.format(expense.getDate()) + "\n");
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
                Date date = DATE_FORMAT.parse(data[3]);

                Expense expense = new Expense(amount, category, description, date);
                expenseList.addExpense(expense);
            }
        } catch (IOException | ParseException e) {
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
            for (String category : Categories.getCategories()) {
                writer.write(category + "|");
            }
        } catch (IOException e) {
            System.out.println("Error saving categories to file.");
        }
    }
}
