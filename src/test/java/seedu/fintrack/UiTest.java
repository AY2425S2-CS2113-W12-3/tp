package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.fintrack.utils.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void printGreeting_displaysGreetingMessage() {
        Ui.printGreeting();
        assertTrue(outputStream.toString().contains("Hello, I am Fin!"));
    }

    @Test
    void printOptions_displaysOptions() {
        Ui.printOptions();
        assertTrue(outputStream.toString().contains("Hey! Here's what I can help you with"));
        assertTrue(outputStream.toString().contains("Add a new expense"));
    }

    @Test
    void printBorder_displaysBorder() {
        Ui.printBorder();
        assertTrue(outputStream.toString().contains("_____________________________________________" +
                "___________________________________"));
    }

    @Test
    void showMessage_displaysGivenMessage() {
        String message = "Test message";
        Ui.showMessage(message);
        assertTrue(outputStream.toString().contains(message));
    }

    @Test
    void showError_displaysErrorMessage() {
        String errorMessage = "Error occurred";
        Ui.showError(errorMessage);
        assertTrue(outputStream.toString().contains(errorMessage));
    }
}
