package seedu.fintrack.utils;


import seedu.fintrack.Savings;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ui {
    //Color codes for the text
    public static String reset = "\u001B[0m";
    public static String cyan = "\u001B[36m";
    public static String green = "\u001B[32m";
    public static String yellow = "\u001B[33m";
    public static String purple = "\u001B[35m";
    public static String red = "\u001B[31m";
    public static String blue = "\u001B[34m";

    public static String bold = "\033[1m";

    private static String border = "________________________________________________________________________________";
    private static String greeting = "Hello, I am Fin! Your go-to expense tracker\nHow can I help you today?";
    private static String logo =
            "   ▄████████  ▄█  ███▄▄▄▄\n" +
                    "  ███    ███ ███  ███▀▀▀██▄\n" +
                    "  ███    █▀  ███▌ ███   ███\n" +
                    " ▄███▄▄▄     ███▌ ███   ███\n" +
                    "▀▀███▀▀▀     ███▌ ███   ███\n" +
                    "  ███        ███  ███   ███\n" +
                    "  ███        ███  ███   ███\n" +
                    "  ███        █▀    ▀█   █▀\n" +
                    "\n" +
                    "    ███        ▄████████    ▄████████  ▄████████    ▄█   ▄█▄\n" +
                    "▀█████████▄   ███    ███   ███    ███ ███    ███   ███ ▄███▀\n" +
                    "   ▀███▀▀██   ███    ███   ███    ███ ███    █▀    ███▐██▀\n" +
                    "    ███   ▀  ▄███▄▄▄▄██▀   ███    ███ ███         ▄█████▀\n" +
                    "    ███     ▀▀███▀▀▀▀▀   ▀███████████ ███        ▀▀█████▄\n" +
                    "    ███     ▀███████████   ███    ███ ███    █▄    ███▐██▄\n" +
                    "    ███       ███    ███   ███    ███ ███    ███   ███ ▀███▄\n" +
                    "   ▄████▀     ███    ███   ███    █▀  ████████▀    ███   ▀█▀\n" +
                    "              ███    ███                           ▀\n";

    private static List<String> tips = List.of(
            "Track your expenses daily to avoid unnecessary spending. Keeping track of your daily expenses\n" +
                    " helps you become more aware of where your money goes. It forces you to evaluate each purchase and \n" +
                    "consider whether it’s essential or a waste. This practice helps build financial discipline, \n" +
                    "preventing you from overspending on small, impulse buys that add up over time. There are plenty of \n" +
                    "apps and tools available to make tracking easy and even enjoyable. By reviewing your spending \n" +
                    "habits regularly, you’ll gain more control over your financial situation.\n",
            "Set aside at least 20% of your income for savings. One of the most powerful ways to build financial \n" +
                    "security is by consistently saving a portion of your income. Aiming for at least 20% of your income\n" +
                    " is a good starting point. By prioritizing savings, you’re creating a buffer for emergencies, \n" +
                    "future investments, or large goals such as buying a home. This habit also helps you build wealth\n " +
                    "over time, as your savings can be invested to earn compound interest. If saving 20% is too much \n" +
                    "at first, start smaller and gradually increase it as your income grows.\n",
            "Avoid impulse purchases by waiting 24 hours before buying non-essentials. Impulse buying is one of the \n" +
                    "easiest ways to overspend without realizing it. Giving yourself a 24-hour waiting period before\n" +
                    " making a purchase allows you to reconsider whether it's truly necessary. Often, you’ll find that\n" +
                    " the urge to buy fades after some time, and you'll be glad you didn’t make the purchase. This\n" +
                    " strategy can be particularly effective for online shopping, where it’s easy to make decisions in\n" +
                    " the moment. By exercising patience, you’ll save money and make more intentional, thoughtful\n" +
                    " purchases.\n",
            "Automate your savings to ensure consistency. Automating your savings means you’re committing to saving\n" +
                    " money before you even have a chance to spend it. By setting up automatic transfers from your\n" +
                    " checking to savings account, you ensure that you’re consistently saving without having to think\n" +
                    " about it. This removes the temptation to spend money that you should be saving and helps make\n" +
                    " saving a seamless part of your financial routine. Over time, this consistency leads to financial\n" +
                    " growth, as the money you save begins to earn interest or contribute to larger financial goals.\n",
            "Review and cancel unused subscriptions to save money. Subscriptions are often small, recurring expenses\n" +
                    " that accumulate unnoticed over time. Review all your subscriptions—streaming services, magazines,\n" +
                    " software, and memberships—and cancel any that you no longer use or need. Even if each subscription\n" +
                    " seems insignificant, they add up over months or years. This simple review can save you hundreds of\n" +
                    " dollars annually. Regularly checking your subscriptions ensures that you're only paying for\n" +
                    " services that add value to your life.\n",
            "Use cash or debit cards instead of credit cards to control spending. Credit cards can make it too easy to\n" +
                    " spend money that you don’t have, leading to debt and high-interest payments. Using cash or debit\n" +
                    " cards gives you a clearer sense of your available funds, making you more mindful of your spending\n" +
                    ". With debit cards, you’re limited to the money in your account, preventing you from overspending.\n" +
                    " Cash, on the other hand, physically limits your spending, making it harder to part with. Using\n" +
                    " these methods encourages you to live within your means, reducing the risk of debt accumulation.\n",
            "Compare prices before making big purchases. Large purchases like electronics, appliances, and furniture\n" +
                    " can quickly drain your bank account. Always compare prices from different retailers before\n" +
                    " committing to a purchase. Take into account factors like delivery costs, return policies, and\n" +
                    " warranty services. Shopping around can help you find the best deal, ensuring that you don’t\n" +
                    " overpay for a product. This small effort can lead to significant savings, especially for\n" +
                    " high-ticket items.\n",
            "Invest early to take advantage of compound interest. The earlier you start investing, the more time your\n" +
                    " money has to grow. Compound interest works by earning interest on the interest that has already\n" +
                    " been added to your principal, which means the longer you leave your money invested, the more it\n" +
                    " multiplies. Starting early allows you to build wealth passively, without needing to contribute\n" +
                    " as much over time. Even small contributions can snowball into significant returns. The earlier\n" +
                    " you begin, the greater the potential for long-term financial success.\n",
            "Set realistic financial goals and review them regularly. Setting clear, realistic financial goals is\n" +
                    " crucial for staying on track. Whether you're saving for a vacation, a new home, or retirement, \n" +
                    "defining your goals gives you something concrete to work toward. It's important to break these \n" +
                    "goals down into smaller, manageable steps, and regularly assess your progress. Reviewing your \n" +
                    "goals helps keep you motivated and allows you to adjust your approach if necessary. By doing so,\n" +
                    " you ensure that you remain focused and on track to achieve financial success.\n",
            "Avoid taking loans for depreciating assets. Loans should generally be used for appreciating assets like\n" +
                    " a home or education, not for things that lose value over time. Taking a loan for a car, for\n" +
                    " example, is often a bad idea because cars depreciate rapidly, meaning you’re left with debt that\n" +
                    " exceeds the value of the asset. Similarly, financing other non-essential items with loans can \n" +
                    "lead to financial strain as you’re paying interest on things that don’t provide long-term value. \n" +
                    "Avoid this trap by saving for depreciating assets instead of financing them.\n",
            "Establish an emergency fund with at least 3-6 months of expenses. Life is unpredictable, and unexpected\n" +
                    " expenses can arise at any time. An emergency fund provides a financial cushion for situations \n" +
                    "like medical bills, car repairs, or job loss. It’s recommended to save at least 3-6 months of \n" +
                    "living expenses in an easily accessible account. This fund ensures that you’re not forced to take\n" +
                    " on debt when something unexpected happens. It gives you peace of mind knowing that you’re\n" +
                    " financially prepared for life's surprises.\n",
            "Review your budget every month and adjust as needed. Your financial situation and goals can change over\n" +
                    " time, so it's essential to review your budget monthly. This allows you to see where you’ve\n" +
                    " overspent or underspent and make adjustments accordingly. You might find areas where you can save\n" +
                    " more, or you may need to reallocate funds to meet new financial priorities. Regularly updating\n" +
                    " your budget ensures that you're always working with accurate, relevant financial data, which\n" +
                    " helps keep your spending and saving on track.\n",
            "Use a budgeting app to track spending and stay on target. Keeping track of your expenses manually can be\n" +
                    " time-consuming and error-prone. A budgeting app can help automate this process and give you a\n" +
                    " clear view of your financial situation at any time. Most apps offer features like expense \n" +
                    "categorization, bill reminders, and savings goals, making it easier to stay on top of your money.\n" +
                    " With real-time tracking, you can make adjustments before you overspend, keeping you on track with\n" +
                    " your financial goals. Budgeting apps simplify the process and take the guesswork out of managing\n" +
                    " your finances.\n",
            "Pay off high-interest debt as soon as possible. High-interest debt, such as credit card debt, can quickly\n" +
                    " spiral out of control. The longer you carry this debt, the more money you end up paying in \n" +
                    "interest, making it harder to get out of debt. Prioritize paying off high-interest loans to\n" +
                    " free up your money for savings and investments. Once you’ve eliminated this debt, you’ll be \n" +
                    "in a better position to save, invest, and make larger financial goals. Tackling high-interest\n" +
                    " debt head-on allows you to break free from its financial strain.\n",
            "Learn about personal finance to make informed decisions. Understanding personal finance concepts is key \n" +
                    "to making smart financial decisions. From budgeting and investing to understanding taxes and debt\n" +
                    " management, knowledge is your greatest asset. The more you learn, the better equipped you'll be\n" +
                    " to navigate your financial journey and avoid common pitfalls. There are many free resources \n" +
                    "available, including books, podcasts, and online courses. Take time to educate yourself so that\n" +
                    " you can confidently manage your money and make decisions that align with your long-term goals.\n",
            "Take advantage of employer-matching retirement contributions. Many employers offer matching contributions \n" +
                    "to your retirement fund, which is essentially free money. If your employer offers this benefit, \n" +
                    "it’s crucial to contribute enough to take full advantage of the match. Failing to do so is like \n" +
                    "leaving free money on the table, which can have a significant impact on your retirement savings \n" +
                    "over time. Employer matches often come with a vesting schedule, meaning the more you contribute, \n" +
                    "the more you’ll receive from your employer. Don’t miss out on this valuable opportunity.\n",
            "Avoid lifestyle inflation—save instead of upgrading your expenses. As your income increases, it can be \n" +
                    "tempting to increase your spending on luxuries or bigger purchases. This phenomenon, known as \n" +
                    "lifestyle inflation, can prevent you from building long-term wealth. Instead of upgrading your \n" +
                    "lifestyle, consider saving or investing the additional income. This will allow you to create \n" +
                    "financial security for the future, rather than falling into the trap of continuously increasing \n" +
                    "your living expenses. By avoiding lifestyle inflation, you ensure that you’re living below your \n" +
                    "means and prioritizing your financial growth.\n",
            "Make meal plans to cut down on food expenses. Food is one of the biggest expenses in a household, and \n" +
                    "without a plan, it can quickly spiral out of control. By meal planning, you can avoid last-minute \n" +
                    "takeout orders and unnecessary impulse buys at the grocery store. Planning meals ahead of time \n" +
                    "allows you to buy ingredients in bulk and minimize food waste. This also helps you stick to a\n" +
                    " grocery budget and reduce the temptation to splurge on items you don't need. Meal planning is a\n" +
                    " simple yet effective way to save money on food.\n",
            "Prioritize needs over wants in your spending habits. Distinguishing between needs and wants is key to \n" +
                    "managing your finances wisely. Needs are essentials—things like rent, utilities, and \n" +
                    "groceries—while wants are non-essential items that provide temporary satisfaction. By focusing \n" +
                    "on your needs first and limiting spending on wants, you’ll ensure that your financial resources \n" +
                    "are used more effectively. While it's okay to indulge occasionally, prioritizing needs helps \n" +
                    "maintain financial stability and prevents unnecessary expenses from taking over.\n",
            "Reinvest dividends and interest to grow your wealth. If you’re invested in dividend-paying stocks or \n" +
                    "interest-bearing accounts, consider reinvesting the earnings rather than withdrawing them. \n" +
                    "Reinvesting allows your investments to grow faster through the power of compounding. Each dividend\n" +
                    " or interest payment adds to your principal balance, allowing you to earn more on your returns.\n" +
                    "Over time, this can lead to significantly higher wealth accumulation. Make the most of your \n" +
                    "earnings by allowing them to grow alongside your principal investments.\n");

    public static void printGreeting() {
        printBorder();
        System.out.println(greeting);
        printBorder();
    }

    public static void printRandomTip() {
        System.out.println("Here is a financial pro-tip:");
        String randomTip = tips.get(ThreadLocalRandom.current().nextInt(tips.size()));
        System.out.println(randomTip);
    }

    public static void printLogo() {
        System.out.println(logo);
    }

    public static void printOptions() {
        String options = "Hey! Here's what I can help you with\n" +
                cyan + "Add a new expense\n" + reset +
                yellow + "Check out this month's spending\n" + reset +
                yellow + "View your spending history\n" + reset +
                purple + "Update an expense entry\n" + reset +
                red + "Delete an expense\n" + reset +
                blue + "Set your monthly budget\n" + reset +
                blue + "Add a recurring expense\n" + reset +
                green + "Create a new category\n" + reset +
                red + "Delete a category\n" + reset +
                cyan + "Update your Income\n" + reset +
                cyan + "Update your monthly savings goal\n" + reset +
                red + "Exit the app\n" + reset +
                "For more info on how to use the commands, type " + bold + "'help'" + reset + ".\n";
        System.out.println(options + border);
    }

    public static void printSavings() {
        float income =   ((float)Savings.getIncome()) / 100;
        float savings = ((float)Savings.getCurrentSavings()) / 100;
        float savingsGoal = ((float)Savings.getSavingsGoal()) / 100;

        System.out.println("Your current income: $" + income);
        System.out.println("Your current savings: $" + savings);
        System.out.println("Your current monthly saving goal: $" + savingsGoal);
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printBorder() {
        System.out.println(border);
    }
}
