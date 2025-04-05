package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Storage;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Date;

public class ExportCommand implements Command {
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat FILE_NAME_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static final String EXPORTS_FOLDER = "exports";

    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        try {
            // Create exports folder if it doesn't exist
            File exportsDir = new File(EXPORTS_FOLDER);
            if (!exportsDir.exists()) {
                if (!exportsDir.mkdir()) {
                    throw new FinTrackException("Failed to create exports directory.");
                }
            }

            // Generate filename with current date and time
            String timestamp = FILE_NAME_FORMAT.format(new Date());
            String fileName = "expenses_" + timestamp + ".csv";
            String filePath = EXPORTS_FOLDER + File.separator + fileName;

            // Create CSV file in exports folder
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            
            // Write CSV header
            writer.write("Amount,Category,Description,Date\n");
            
            // Write each expense as a CSV row
            for (Expense expense : expenseList.getExpenseList()) {
                double amountInDollars = expense.getAmount() / 100.0;
                String formattedAmount = String.format("$%.2f", amountInDollars);
                String date = DATE_TIME_FORMAT.format(expense.getDate());
                
                // Escape commas in description and category
                String description = expense.getDescription().contains(",") 
                    ? "\"" + expense.getDescription() + "\"" 
                    : expense.getDescription();
                String category = expense.getCategory().contains(",") 
                    ? "\"" + expense.getCategory() + "\"" 
                    : expense.getCategory();
                
                writer.write(formattedAmount + "," + category + "," + description + "," + date + "\n");
            }
            
            writer.close();
            ui.showMessage("Expenses exported to " + filePath + " successfully!");
            ui.printBorder();
        } catch (IOException e) {
            throw new FinTrackException("Error exporting expenses to CSV: " + e.getMessage());
        }
    }
}
