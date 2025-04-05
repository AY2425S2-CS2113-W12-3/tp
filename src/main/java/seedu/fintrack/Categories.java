package seedu.fintrack;

import java.util.ArrayList;
import seedu.fintrack.utils.Ui;

public class Categories {
    private static ArrayList<String> categories = new ArrayList<>();

    static {
        categories.add("Food");
        categories.add("Transport");
        categories.add("Entertainment");
        categories.add("Household");
        categories.add("Utilities");
    }

    public static String getCategory(int index) {
        return categories.get(index - 1);
    }

    public static int size() {
        return categories.size();
    }

    public static void printCategories() {
        System.out.println(Ui.bold + "Categories:" + Ui.reset);
        if(categories.isEmpty()) {
            System.out.println("There are currently no categories! Use the 'category add' command to add a category.");
        } else {
            int index = 1;
            for (String category : categories) {
                System.out.println(index + ". " + category);
                index++;
            }
        }
    }

    public static void printCustomCategories() {
        System.out.println(Ui.bold + "Custom Categories:" + Ui.reset);
        int numberOfDefaultCategories = 5;
        if(categories.size() == numberOfDefaultCategories) {
            System.out.println("There are currently no custom categories! Use the 'category add' command to add a" +
                    " category.");
        } else {
            int index = 1;
            for (int i = numberOfDefaultCategories; i < categories.size(); i++) {
                System.out.println(index + ". " + categories.get(i));
                index++;
            }
        }
    }

    public static void addCategory(String category) {
        categories.add(category);
    }

    public static void removeCategory(int index) {
        categories.remove(index-1);
    }

    public static ArrayList<String> getCategories() {
        return categories;
    }
}
