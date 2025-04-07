package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.util.HashSet;
import java.util.Set;

public class AddCategoryCommand implements Command {
    private final Parser parser;

    public AddCategoryCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {

        categories.printCategories();
        String[] newCategories = parser.readCategories();
        if (newCategories == null || newCategories.length == 0 || newCategories.length == 1 &&
                newCategories[0].isEmpty()) {
            ui.showError("Empty Input. Please enter a category.");
            ui.printBorder();
            return;
        }

        for(String newCategory : newCategories) {
            if(categories.getCategories().contains(newCategory)) {
                ui.showError("Category already exists. Please choose a different category.");
                ui.printBorder();
                return;
            }
        }

        Set<String> seen = new HashSet<>();
        for (String newCategory : newCategories) {
            if (!seen.add(newCategory)) {
                ui.showError("Input contains duplicate categories. Please try again.");
                ui.printBorder();
                return;
            }
        }

        for(String newCategory : newCategories) {
            categories.addCategory(newCategory);
        }

        storage.saveCategoriesToFile();
        String categoriesDisplay = String.join(", ", newCategories);

        if(newCategories.length == 1) {
            ui.showMessage(categoriesDisplay + " has been added to the list of categories.");
        } else {
            ui.showMessage(categoriesDisplay + " have been added to the list of categories.");
        }

        ui.printBorder();

    }
}
