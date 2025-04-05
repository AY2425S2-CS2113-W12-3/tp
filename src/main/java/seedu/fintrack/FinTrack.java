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
        AllCommands commands = new AllCommands(expenseList, parser);

        Storage storage = new Storage();
        storage.loadExpensesFromFile(expenseList);
        storage.loadCategoriesFromFile();

        //Loading the savings.txt file will also call the constructor for a new Savings object
        Savings savings = storage.loadSavingsFromFile();
        savings.addIncome();

        boolean isRunning = true;
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
