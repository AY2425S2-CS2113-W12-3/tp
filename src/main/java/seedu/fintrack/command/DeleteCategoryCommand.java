package seedu.fintrack.command;

import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Parser;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

        Integer[] categoryIndexes = parser.readCategoryIndexes();

        Set<Integer> seen = new HashSet<>();
        for (Integer index : categoryIndexes) {
            if (!seen.add(index)) {
                ui.showError("Input contains duplicate indexes. Please try again.");
                ui.printBorder();
                return;
            }
        }

        if (categoryIndexes == null || categoryIndexes.length == 0 || categoryIndexes.length == 1 &&
                categoryIndexes[0] == null) {
            ui.showError("Empty Input. Please provide valid index/indexes.");
            ui.printBorder();
            return;
        }

        for(Integer categoryIndex : categoryIndexes) {
            try {
                if (categoryIndex <= 0 || categoryIndex > categories.size() - numberOfDefaultCategories) {
                    ui.showError(categoryIndex + " is an invalid index. Please try again.");
                    ui.printBorder();
                    return;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid category index. Please try again.");
            }
        }

        Arrays.sort(categoryIndexes, Collections.reverseOrder());

        for(Integer categoryIndex : categoryIndexes) {
            String deletedCategory = categories.getCategory(categoryIndex + numberOfDefaultCategories);
            categories.removeCategory(categoryIndex + numberOfDefaultCategories);
            ui.showMessage(deletedCategory + " has been deleted.");
        }

        storage.saveCategoriesToFile();
        ui.printBorder();

    }
}
