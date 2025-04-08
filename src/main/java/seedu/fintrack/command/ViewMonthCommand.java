package seedu.fintrack.command;

import seedu.fintrack.Expense;
import seedu.fintrack.ExpenseList;
import seedu.fintrack.Savings;
import seedu.fintrack.utils.FinTrackException;
import seedu.fintrack.utils.Ui;
import seedu.fintrack.Categories;
import seedu.fintrack.utils.Storage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewMonthCommand implements Command {
    @Override
    public void execute(ExpenseList expenseList, Ui ui, Storage storage, Categories categories)
            throws FinTrackException {
        Date now = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM-yyyy");
        String currentMonth = monthFormat.format(now);
        ui.showMessage("Month: " + currentMonth);
        ui.showMessage("Your current income: " + Savings.getIncome());
        int monthlyExpenses = Savings.calculateMonthlyExpenses(ExpenseList.getExpenseList());

        if (monthlyExpenses < Savings.getSavingsGoal()) {
            ui.showMessage("You have yet to hit your savings goal You can still spend "
                    + (Savings.getSavingsGoal() - monthlyExpenses));
        } else {
            ui.showMessage("You have exceeded your monthly savings goal by "
                    + (monthlyExpenses - Savings.getSavingsGoal()));
        }

        ui.showMessage("Here is your expenses for this month: ");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.getExpense(i);
            if (monthFormat.format(expense.getDate()).equals(currentMonth)) {
                ui.showMessage(i + 1 + ": " + expense.getDescription() +
                        " - " + expense.getAmount() + " cents");
            }
        }

        provideFinancialTips(expenseList, ui);

        ui.printBorder();
    }

    private void provideFinancialTips(ExpenseList expenseList, Ui ui) {
        int totalExpenses = Savings.calculateMonthlyExpenses(expenseList.getExpenseList());
        int income = Savings.getIncome();
        int luxurySpending = ExpenseList.getTotalByCategory(expenseList, "Luxury");
        int foodSpending = ExpenseList.getTotalByCategory(expenseList,"Food");
        int subscriptionSpending = ExpenseList.getTotalByCategory(expenseList,"Subscription");
        int savings = income - totalExpenses;

        if (luxurySpending > totalExpenses * 0.3) {
            ui.showMessage("\n⚠️ *High Luxury Spending Detected!* ⚠️");
            ui.showMessage("\n");
            ui.showMessage("You spent over 30% of your total expenses on luxury items.");
            ui.showMessage("Consider setting a strict budget for non-essentials.");
            ui.showMessage("\n");
            ui.showMessage("Impulse purchases on high-end items can add up quickly.");
            ui.showMessage("\n");
            ui.showMessage("Try using the 48-hour rule before buying something expensive!");
            ui.showMessage("\n");
            ui.showMessage("If you often regret purchases, create a wish list and revisit it later.");
            ui.showMessage("\n");
            ui.showMessage("Think about whether luxury items align with your long-term goals.");
            ui.showMessage("\n");
            ui.showMessage("Ask yourself: Does this bring value to my life, or is it just a momentary desire?");
            ui.showMessage("\n");
            ui.showMessage("Consider redirecting some of this money into investments or savings.");
        }

        if (foodSpending > totalExpenses * 0.25) {
            ui.showMessage("\n🍔 *Eating Out Too Much?* 🍔");
            ui.showMessage("\n");
            ui.showMessage("Food expenses are taking up a large chunk of your budget.");
            ui.showMessage("\n");
            ui.showMessage("Consider meal prepping at home to save money!");
            ui.showMessage("\n");
            ui.showMessage("Try setting a weekly dining-out budget and sticking to it.");
            ui.showMessage("\n");
            ui.showMessage("Grocery shopping with a plan can help you avoid unnecessary spending.");
            ui.showMessage("\n");
            ui.showMessage("Opt for loyalty programs or discount meal plans to reduce costs.");
            ui.showMessage("\n");
            ui.showMessage("Track how much you're spending on takeout versus home-cooked meals.");
            ui.showMessage("\n");
            ui.showMessage("Consider cooking in bulk and freezing meals for convenience.");
            ui.showMessage("\n");
            ui.showMessage("Look into healthy budget-friendly recipes that are both cost-effective and nutritious.");
        }

        if (subscriptionSpending > income * 0.1) {
            ui.showMessage("\n📺 *Subscription Overload!* 📺");
            ui.showMessage("\n");
            ui.showMessage("Your recurring subscriptions exceed 10% of your income!");
            ui.showMessage("\n");
            ui.showMessage("Do you really need all those streaming services?");
            ui.showMessage("\n");
            ui.showMessage("Audit your subscriptions and cancel those you don’t use often.");
            ui.showMessage("\n");
            ui.showMessage("Look for annual billing discounts on services you frequently use.");
            ui.showMessage("\n");
            ui.showMessage("Consider sharing family plans to split costs.");
            ui.showMessage("\n");
            ui.showMessage("Check your bank statements for forgotten subscriptions.");
            ui.showMessage("\n");
            ui.showMessage("If you aren’t using a service regularly, pause it instead of canceling.");
        }

        if (expenseList.size() > 20) {
            ui.showMessage("\n🛒 *Impulse Buying Alert!* 🛒");
            ui.showMessage("\n");
            ui.showMessage("You have a high number of transactions this month.");
            ui.showMessage("\n");
            ui.showMessage("Small purchases can add up! Consider tracking impulse buys.");
            ui.showMessage("\n");
            ui.showMessage("Try implementing a 'no-spend' day each week to reset habits.");
            ui.showMessage("\n");
            ui.showMessage("Leave your credit card at home for unnecessary purchases!");
            ui.showMessage("\n");
            ui.showMessage("Make a list before shopping and stick to it.");
            ui.showMessage("\n");
            ui.showMessage("Delete shopping apps from your phone to reduce temptation.");
            ui.showMessage("\n");
            ui.showMessage("Unsubscribe from marketing emails that encourage impulse buys.");
        }

        if (savings <= 0) {
            ui.showMessage("\n💰 *No Savings This Month?* 💰");
            ui.showMessage("\n");
            ui.showMessage("You haven’t saved anything this month.");
            ui.showMessage("\n");
            ui.showMessage("Try setting aside savings *before* you start spending.");
            ui.showMessage("\n");
            ui.showMessage("Consider automating savings transfers on payday.");
            ui.showMessage("\n");
            ui.showMessage("Even saving a small amount regularly can add up over time!");
            ui.showMessage("\n");
            ui.showMessage("Think about future financial security and emergency funds.");
            ui.showMessage("\n");
            ui.showMessage("Explore high-yield savings accounts to make your money work for you.");
        }

        if (savings > 0 && savings >= Savings.getSavingsGoal()) {
            ui.showMessage("\n✅ *Great Job Saving!* ✅");
            ui.showMessage("\n");
            ui.showMessage("You have met your savings goal for the month!");
            ui.showMessage("\n");
            ui.showMessage("Consider investing some of your savings for long-term growth.");
            ui.showMessage("\n");
            ui.showMessage("Keep tracking your expenses to stay on top of your finances.");
            ui.showMessage("\n");
            ui.showMessage("Think about setting new financial goals for the next quarter.");
            ui.showMessage("\n");
            ui.showMessage("Diversify your savings—split between emergency funds and investments.");
        }

        if (Math.abs(income - totalExpenses) > income * 0.5) {
            ui.showMessage("\n⚖️ *Inconsistent Income Detected!* ⚖️");
            ui.showMessage("\n");
            ui.showMessage("Your expenses and income fluctuate significantly.");
            ui.showMessage("\n");
            ui.showMessage("If you have an irregular income, consider building a buffer fund.");
            ui.showMessage("\n");
            ui.showMessage("Try using the '50/30/20' rule to allocate savings, needs, and wants.");
            ui.showMessage("\n");
            ui.showMessage("Consider taking on side gigs to stabilize earnings.");
            ui.showMessage("\n");
            ui.showMessage("Avoid committing to fixed expenses if your income varies month to month.");
        }
    }
}
