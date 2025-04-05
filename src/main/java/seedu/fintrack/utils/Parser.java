package seedu.fintrack.utils;

import seedu.fintrack.Expense;
import seedu.fintrack.Categories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Parser {
    private Scanner scanner;

    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }


    public String getCommandInput() {
        //System.out.println("Enter command (e.g., 'add', 'viewmonth', 'history', 'update', 'delete', 'budget'" +
        //        ", 'recurring', 'category', 'exit')\nor type 'help' to see the options again :] ");
        Ui.printBorder();
        return scanner.nextLine().trim().toLowerCase();
    }


    public String promptInput(String prompt) {
        System.out.print(prompt + " ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
            System.out.println("Exiting program...");
            System.exit(0); // Or throw a custom ExitException and catch it in your main loop.
        }
        return input;
    }



    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting program...");
                System.exit(0);
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }


    public Date readDate(String prompt) throws FinTrackException {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting program...");
                System.exit(0);
            }
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(input);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Expected yyyy-MM-dd, got: " + input);
            }
        }
    }


    /**
     * Reads expense details by prompting user for each detail separately.
     * @return Expense object containing the details.
     * @throws FinTrackException if input is invalid or missing.
     */
    public Expense readExpenseDetails() throws FinTrackException {
        System.out.println(Ui.bold + "Enter expense details in as follows:" + Ui.reset);
        System.out.println("<dollars>, <cents>, <category index>, <description>, <date (yyyy-MM-dd)>\n");
        Categories.printCategories();
        String input = scanner.nextLine();
        return parseExpenseDetails(input);
    }

    /**
     * Parses expense details from a single input line.
     * @param inputLine Line containing expense details separated by commas.
     * @return Expense object containing the details.
     * @throws FinTrackException if input is invalid or missing.
     */
    public Expense parseExpenseDetails(String inputLine) throws FinTrackException {
        String[] parts = inputLine.split(",");
        if (parts.length < 5) {
            throw new FinTrackException("Insufficient details provided.");
        }
        try {
            int dollars = Integer.parseInt(parts[0].trim());
            int cents = Integer.parseInt(parts[1].trim());
            if (dollars < 0 || cents < 0) {
                throw new FinTrackException("Expense amount cannot be negative.");
            }
            if (cents > 99) {
                throw new FinTrackException("Cents value cannot exceed 99.");
            }
            double amountDouble = dollars + (double) cents / 100.0;
            amountDouble = Math.round(amountDouble * 100.0) / 100.0; // Round to 2 dp
            int amount = (int) (amountDouble * 100); // Store as cents to avoid floating point issues
            int categoryIndex = Integer.parseInt(parts[2].trim());
            String category = Categories.getCategory(categoryIndex);
            String description = parts[3].trim();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4].trim());
            return new Expense(amount, category, description, date);
        } catch (NumberFormatException e) {
            throw new FinTrackException("Invalid number format for dollars or cents.");
        } catch (Exception e) {
            throw new FinTrackException("Error parsing expense details: " + e.getMessage());
        }
    }
}
