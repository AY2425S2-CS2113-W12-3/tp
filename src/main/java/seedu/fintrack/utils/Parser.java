package seedu.fintrack.utils;

import seedu.fintrack.Expense;
import seedu.fintrack.Categories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Calendar;


public class Parser {
    private static final SimpleDateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int MAX_EXPENSE_AMOUNT = 100000000; // 1 million dollars in cents
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

    /**
     * Reads an integer from user input with support for cancellation using '0'.
     * @param prompt The prompt to display to the user
     * @return The entered integer, or -1 if the user enters '0' to cancel
     * @throws FinTrackException if the input is invalid
     */
    public int readIntWithCancel(String prompt) throws FinTrackException {
        while (true) {
            System.out.print(prompt + " (Enter 0 to cancel) ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting program...");
                System.exit(0);
            }
            if (input.equals("0")) {
                return -1; // Special value to indicate cancellation
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new FinTrackException("Invalid number, please try again.");
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

    private void validateDateTime(String dateStr, String timeStr) throws FinTrackException {
        // Validate date format and components
        String[] dateParts = dateStr.split("-");
        if (dateParts.length != 3) {
            throw new FinTrackException("Invalid date format. Please use yyyy-MM-dd format.");
        }

        try {
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);

            // Validate month
            if (month < 1 || month > 12) {
                throw new FinTrackException("Invalid month. Month must be between 1 and 12.");
            }

            // Validate day based on month
            int maxDays;
            if (month == 2) { // February
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    maxDays = 29; // Leap year
                } else {
                    maxDays = 28;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                maxDays = 30;
            } else {
                maxDays = 31;
            }

            if (day < 1 || day > maxDays) {
                throw new FinTrackException("Invalid day. Day must be between 1 and " + maxDays +
                        " for month " + month + ".");
            }

            // Validate time if provided
            if (timeStr != null) {
                String[] timeParts = timeStr.split(":");
                if (timeParts.length != 3) {
                    throw new FinTrackException("Invalid time format. Please use HH:mm:ss format.");
                }

                int hours = Integer.parseInt(timeParts[0]);
                int minutes = Integer.parseInt(timeParts[1]);
                int seconds = Integer.parseInt(timeParts[2]);

                if (hours < 0 || hours > 23) {
                    throw new FinTrackException("Invalid hours. Hours must be between 0 and 23.");
                }
                if (minutes < 0 || minutes > 59) {
                    throw new FinTrackException("Invalid minutes. Minutes must be between 0 and 59.");
                }
                if (seconds < 0 || seconds > 59) {
                    throw new FinTrackException("Invalid seconds. Seconds must be between 0 and 59.");
                }
            }
        } catch (NumberFormatException e) {
            throw new FinTrackException("Invalid number format in date or time.");
        }
    }

    /**
     * The user is expected to enter details separated by commas:
     * dollars, cents, category index, description[, date(yyyy-MM-dd)[, time(HH:mm:ss)]]
     * If date is not provided, current date and time will be used
     * If only date is provided without time, current time will be used
     */
    public Expense readExpenseDetails() throws FinTrackException {
        System.out.println(Ui.bold + "Enter expense details in as follows:" + Ui.reset);
        System.out.println("<dollars>,<cents>,<category index>,<description>" +
                ",<date (yyyy-MM-dd)>,<time (HH:mm:ss)>");
        System.out.println("Note: Date and time are optional. Current date/time will be used if not provided.");
        System.out.println("Note: The maximum amount that can be entered is $1,000,000.00 (one million dollars).");
        System.out.println("Note: Cents must be between 0 and 99 (2 digits only).");
        System.out.println("Note: Type 'NAH' to cancel the add operation.\n");
        Categories.printCategories();
        String input = scanner.nextLine().trim();
        
        // Check for cancellation
        if (input.equalsIgnoreCase("NAH")) {
            return null; // Special value to indicate cancellation
        }

        // Split by commas and handle multiple spaces
        String[] parts = input.split("\\s*,\\s*");
        if (parts.length < 4) {
            throw new FinTrackException("Insufficient details provided. At least dollars, cents, category index, " 
                + "and description are required.");
        }
        try {
            // Trim each part to handle any remaining spaces
            int dollars = Integer.parseInt(parts[0].trim());
            int cents = Integer.parseInt(parts[1].trim());
            
            // Validate that neither dollars nor cents are negative
            if (dollars < 0 || cents < 0) {
                throw new FinTrackException("Dollars and cents cannot be negative.");
            }
            
            // Validate that cents are limited to 2 digits (0-99)
            if (cents > 99) {
                throw new FinTrackException("Cents must be between 0 and 99 (2 digits only).");
            }
            
            int amount = dollars * 100 + cents;
            
            // Validate that the amount does not exceed 1 million dollars
            if (amount > MAX_EXPENSE_AMOUNT) {
                throw new FinTrackException("Expense amount cannot exceed $1,000,000.00 (one million dollars).");
            }
            
            int categoryIndex = Integer.parseInt(parts[2].trim());
            String category = Categories.getCategory(categoryIndex);
            String description = parts[3].trim();
            Date date;

            if (parts.length >= 6) {
                // Both date and time provided
                String dateStr = parts[4].trim();
                String timeStr = parts[5].trim();
                validateDateTime(dateStr, timeStr);
                String dateTimeStr = dateStr + " " + timeStr;
                try {
                    date = DATE_TIME_FORMAT.parse(dateTimeStr);
                } catch (ParseException e) {
                    throw new FinTrackException("Invalid date/time format. Please use yyyy-MM-dd HH:mm:ss format.");
                }
            } else if (parts.length == 5) {
                // Only date provided, use current time
                String dateStr = parts[4].trim();
                validateDateTime(dateStr, null);
                try {
                    date = DATE_ONLY_FORMAT.parse(dateStr);
                    // Combine provided date with current time
                    Calendar cal = Calendar.getInstance();
                    Calendar now = Calendar.getInstance();
                    cal.setTime(date);
                    cal.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
                    cal.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
                    cal.set(Calendar.SECOND, now.get(Calendar.SECOND));
                    date = cal.getTime();
                } catch (ParseException e) {
                    throw new FinTrackException("Invalid date format. Please use yyyy-MM-dd format.");
                }
            } else {
                // No date/time provided, use current date and time
                date = new Date();
            }

            return new Expense(amount, category, description, date);
        } catch (NumberFormatException e) {
            throw new FinTrackException("Invalid number format in dollars, cents, or category index.");
        } catch (Exception e) {
            throw new FinTrackException("Error parsing expense details: " + e.getMessage());
        }
    }
}


