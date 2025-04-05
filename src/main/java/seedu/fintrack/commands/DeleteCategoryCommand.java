package seedu.fintrack.commands;

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
    public void execute() throws FinTrackException {
        Categories.printCategories();
        if(Categories.getCategories().isEmpty()) {
            Ui.printBorder();
            return;
        }
        int index = parser.readInt("Enter the index of the category to delete:");
        if (index <= 0 || index > Categories.size()) {
            Ui.showError("Invalid index.");
            Ui.printBorder();
            return;
        }
        String deletedCategory = Categories.getCategory(index);
        Categories.removeCategory(index);
        Ui.showMessage(deletedCategory + " has been deleted.");
        Storage.saveCategoriesToFile();
        Ui.printBorder();
    }
}
