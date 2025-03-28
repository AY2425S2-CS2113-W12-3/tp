package seedu.fintrack.utils;


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

    public static void printGreeting() {
        printBorder();
        System.out.println(greeting);
        printBorder();
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
                red + "Exit the app\n" + reset +
                "For more info on how to use the commands, type " + bold + "'help'" + reset + ".\n";
        System.out.println(options + border);
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
