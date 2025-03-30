package seedu.fintrack;

import java.util.ArrayList;
import seedu.fintrack.utils.Ui;

public class Categories {
    private static ArrayList<String> categories = new ArrayList<>();


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
