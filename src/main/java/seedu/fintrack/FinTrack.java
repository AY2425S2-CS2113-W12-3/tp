package seedu.fintrack;

import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

import java.util.Scanner;


public class FinTrack {
    /**
     * Main entry-point for the java.fintrack.FinTrack application.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Do the initial setup for the application, which will happen once at the start.
        //Ui.printRandomTip();
        Ui.printGreeting();
        //Ui.printLogo();
        Ui.printSavings();
        Ui.printOptions();

        //String input = sc.nextLine();
        ExpenseList expenseList = new ExpenseList();
        Parser parser = new Parser(sc);
        AllCommands commands = new AllCommands(expenseList, parser, new Storage(), new Ui(), new Categories());

        Storage storage = new Storage();
        storage.loadExpensesFromFile(expenseList);
        storage.loadCategoriesFromFile();
        storage.loadRecurringExpensesFromFile(expenseList);

        //Loading the savings.txt file will also call the constructor for a new Savings object
        Savings savings = storage.loadSavingsFromFile();
        savings.addIncome();

        if (!expenseList.getRecurringExpenses().isEmpty()) {
            expenseList.addAllRecurringExpenses();
            storage.saveExpensesToFile(expenseList);
        }

        boolean isRunning = true;
        if (args.length > 0 && args[0].equals("--apply-only")) {
            isRunning = false;
        }

        //Main application loop starts, user input is read and processed
        //until the user enters the "exit" command.
        while (sc.hasNextLine() && isRunning) {
            String rawInput = parser.getCommandInput();
            if (rawInput.equalsIgnoreCase("exit") || rawInput.equalsIgnoreCase("quit")) {
                commands.fetchCommand("exit");
                isRunning = false;
            } else {
                commands.fetchCommand(rawInput);
            }
        }

    }
}
