package seedu.fintrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    void testPrintCategoriesOutput() {
        Categories.addCategory("Food");
        Categories.addCategory("Health");

        // This is a visual check; alternatively, you can capture System.out
        System.out.println("Expected output:");
        System.out.println("\u001B[1mCategories:\u001B[0m");
        System.out.println("1. Food");
        System.out.println("2. Health");

        System.out.println("Actual output:");
        Categories.printCategories();
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
}
