package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

public class DeleteCategoryCommand implements Command {
    private final Parser parser;

    public DeleteCategoryCommand(Parser parser) {
        this.parser = parser;
    }

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        categories.printCustomCategories();
        int numberOfDefaultCategories = 5;
        if (categories.size() == numberOfDefaultCategories) {
            ui.printBorder();
            return;
        }
        int index = parser.readInt("Enter the index of the category to delete:");
        if (index <= 0 || index > categories.size() - numberOfDefaultCategories) {
            ui.showError("Invalid index.");
            ui.printBorder();
            return;
        }
        String deletedCategory = categories.getCategory(index + numberOfDefaultCategories);
        categories.removeCategory(index + numberOfDefaultCategories);
        ui.showMessage(deletedCategory + " has been deleted.");
        storage.saveCategoriesToFile();
        ui.printBorder();
    }
}
