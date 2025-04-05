package seedu.fintrack.commands;

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
    public void execute() throws FinTrackException {
        String newCategory = parser.promptInput("Please enter the name of the new category:");
        if (newCategory.isEmpty()) {
            Ui.showError("Empty input. Please enter a category.");
            Ui.printBorder();
            return;
        }
        if (Categories.getCategories().contains(newCategory)) {
            Ui.showError("Category already exists.");
            Ui.printBorder();
            return;
        }
        Categories.addCategory(newCategory);
        Ui.showMessage(newCategory + " has been added to the list of categories.");
        Storage.saveCategoriesToFile();
        Ui.printBorder();
    }
}
