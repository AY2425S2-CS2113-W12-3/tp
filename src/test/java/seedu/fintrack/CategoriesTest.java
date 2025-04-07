package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CategoriesTest {

    @BeforeEach
    void setUp() {
        // Clear all categories before each test to ensure test isolation
        Categories.getCategories().clear();
    }

    @Test
    void testAddCategory() {
        Categories.addCategory("Food");
        assertEquals(1, Categories.size());
        assertEquals("Food", Categories.getCategory(1));
    }

    @Test
    void testGetCategory() {
        Categories.addCategory("Transport");
        Categories.addCategory("Entertainment");
        assertEquals("Transport", Categories.getCategory(1));
        assertEquals("Entertainment", Categories.getCategory(2));
    }

    @Test
    void testRemoveCategory() {
        Categories.addCategory("Bills");
        Categories.addCategory("Shopping");
        Categories.removeCategory(1);
        assertEquals(1, Categories.size());
        assertEquals("Shopping", Categories.getCategory(1));
    }

    @Test
    void testSize() {
        assertEquals(0, Categories.size());
        Categories.addCategory("Groceries");
        assertEquals(1, Categories.size());
    }

    @Test
    void testGetCategoriesReturnsCorrectList() {
        Categories.addCategory("Utilities");
        Categories.addCategory("Travel");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Utilities");
        expected.add("Travel");

        assertEquals(expected, Categories.getCategories());
    }


    @Test
    void testPrintCategoriesOutputs() {

        Categories.addCategory("Food");
        Categories.addCategory("Health");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Categories.printCategories();
        } finally {
            System.setOut(originalOut);
        }

        // Build expected output
        String expectedOutput = "\u001B[1mCategories:\u001B[0m" + System.lineSeparator() +
                "1. Food" + System.lineSeparator() +
                "2. Health" + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    void testPrintCustomCategoriesOutput() {
        //Default categories
        addDefaultCategories();

        //Custom categories
        Categories.addCategory("Subscriptions");
        Categories.addCategory("Health");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Categories.printCustomCategories();
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }

        String expectedOutput = "\u001B[1mCustom Categories:\u001B[0m" + System.lineSeparator() +
                "1. Subscriptions" + System.lineSeparator() +
                "2. Health" + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testPrintCustomCategoriesIfNoCustomCategoriesFound() {
        //Default categories
        addDefaultCategories();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Categories.printCustomCategories();
        } finally {
            // Reset System.out
            System.setOut(originalOut);
        }

        String expectedOutput = "\u001B[1mCustom Categories:\u001B[0m" + System.lineSeparator() +
                "There are currently no custom categories! Use the 'category add' command to add a" +
                " category." + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    void testGetCategoryThrowsExceptionWhenOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Categories.getCategory(1);
        });
    }

    @Test
    void testRemoveCategoryThrowsExceptionWhenOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Categories.removeCategory(1);
        });
    }

    void addDefaultCategories() {
        Categories.addCategory("Food");
        Categories.addCategory("Transport");
        Categories.addCategory("Entertainment");
        Categories.addCategory("Household");
        Categories.addCategory("Utilities");
    }

}
