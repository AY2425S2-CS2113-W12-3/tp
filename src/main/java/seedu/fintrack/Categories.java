package seedu.fintrack;

import java.util.ArrayList;

import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Ui;

public class Categories {
    private static ArrayList<String> categories = new ArrayList<>();

    static {
        categories.add("Food");
        categories.add("Transport");
        categories.add("Entertainment");
        categories.add("Household");
        categories.add("Utilities");
        categories.add("Luxury");
    }

    public static String getCategory(int index) {
        return categories.get(index - 1);
    }

    public static int size() {
        return categories.size();
    }

    public static void printCategories() {
        try {
            System.out.println(Ui.bold + "Categories:" + Ui.reset);
            if (categories.isEmpty()) {
                throw new FinTrackException("Category list is empty.");
            } else {
                int index = 1;
                for (String category : categories) {
                    System.out.println(index + ". " + category);
                    index++;
                }
            }
        } catch (FinTrackException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printCustomCategories() {
        try {
            System.out.println(Ui.bold + "Custom Categories:" + Ui.reset);
            int numberOfDefaultCategories = 5;
            if (categories.isEmpty()) {
                throw new FinTrackException("Category list is empty.");
            }
            if (categories.size() == numberOfDefaultCategories) {
                System.out.println("There are currently no custom categories! Use the 'category add' command to add a" +
                        " category.");
            } else {
                int index = 1;
                for (int i = numberOfDefaultCategories; i < categories.size(); i++) {
                    System.out.println(index + ". " + categories.get(i));
                    index++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCategory(String category) {
        categories.add(category);
    }

    public static void removeCategory(int index) {
        categories.remove(index - 1);
    }

    public static ArrayList<String> getCategories() {
        return categories;
    }
}
