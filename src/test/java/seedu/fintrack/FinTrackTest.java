package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Scanner;

class FinTrackTest {
    private AllCommands commands;
    private ExpenseList expenseList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        expenseList = new ExpenseList();
        // Create a default scanner with an empty input; individual tests will override this if needed.
        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        Parser parser = new Parser(scanner);
        commands = new AllCommands(expenseList, parser, new Storage(), new Ui(), new Categories());
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream)); // Redirect output for testing
    }
}




