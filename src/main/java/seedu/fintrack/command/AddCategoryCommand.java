package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class AddCategoryCommand implements Command {
    private final Parser parser;

    public AddCategoryCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        String newCategory = parser.promptInput("Please enter the name of the new category:");
        if (newCategory.isEmpty()) {
            ui.showError("Empty input. Please enter a category.");
            ui.printBorder();
            return;
        }
        if (categories.getCategories().contains(newCategory)) {
            ui.showError("Category already exists.");
            ui.printBorder();
            return;
        }
        categories.addCategory(newCategory);
        ui.showMessage(newCategory + " has been added to the list of categories.");
        storage.saveCategoriesToFile();
        ui.printBorder();
    }
}
