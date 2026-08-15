import java.util.Scanner;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Singleton
        BookstoreManager manager = BookstoreManager.getInstance();
        BookstoreManager secondReference = BookstoreManager.getInstance();
        
        System.out.println("=========================================================================================");
        System.out.println("                         WELCOME TO BOOKSTORE MANAGEMENT SYSTEM                         ");
        System.out.println("=========================================================================================");
        System.out.println("Singleton test:");
        System.out.println("Ref 1 : " + manager.hashCode());
        System.out.println("Ref 2 : " + secondReference.hashCode());
        System.out.println("Identical Instances: " + (manager == secondReference));
        System.out.println("=========================================================================================");

        // Set up observers
        CustomerNotificationListener customerNotifier = new CustomerNotificationListener();
        InventoryLogger auditLogger = new InventoryLogger();
        manager.registerObserver(customerNotifier);
        manager.registerObserver(auditLogger);

        // Add a new book
        System.out.println("\nAdding a new book...");
        Book dynamicBook = BookFactory.createBook("PRINTED", "Refactoring: Improving Code Design", "Martin Fowler", 49.99, 1.15);
        manager.addBook(dynamicBook);

        System.out.println("Inventory information:");
        BookFactory factory = new BookFactory();
        BatchInventoryLoader batchLoader = new BatchInventoryLoader(factory);
        
        double totalAssetValue = manager.calculateTotalInventoryValue();
        System.out.printf("Total inventory value: $%.2f%n", totalAssetValue);
        
        List<Book> budgetBooks = manager.filterBooksByMaxPrice(20.00);
        System.out.println("Books under $20: " + budgetBooks.size());
        System.out.println("-----------------------------------------------------------------------------------------");

        Calculable calculator = new BillCalculator();
        Printable invoicePrinter = new ConsoleInvoicePrinter();

        System.out.print("\nPlease enter the customer's name: ");
        String customerName = scanner.nextLine().trim();

        while (customerName.isEmpty()) {
            System.out.print("Oops! Customer name is missing. Please enter it: ");
            customerName = scanner.nextLine().trim();
        }

        Customer customer = new Customer(101, customerName);
        Purchase purchase = new Purchase((int) (1000 + Math.random() * 9000), customer);

        boolean keepShopping = true;
        while (keepShopping) {
            manager.displayInventory();
            System.out.print("\nEnter the Book ID you wish to purchase (1 - " 
            + manager.getInventorySize() + ") or enter '0' when your book selection is complete: ");

            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine();

                if (selection == 0) {
                    keepShopping = false;
                } else if (selection > 0 && selection <= manager.getInventorySize()) {
                    Book selectedBook = manager.getBookByIndex(selection - 1);
                    purchase.addBook(selectedBook);
                } else {
                    System.out.println("[ERROR] Book selection failed. Enter a valid ID from the available list.");
                }
            } else {
                System.out.println("[WARNING] Incorrect input format. Enter a valid value to continue.");
                scanner.nextLine();
            }
        }

        if (!purchase.getPurchasedBooks().isEmpty()) {
            // 4. Strategy Pattern Selection
            System.out.println("\n-----------------------------------------------------------------------------------------");
            System.out.println("Choose a discount:");
            System.out.println("1. Regular Purchase (No Discount)");
            System.out.println("2. Premium Member Discount (10% OFF)");
            System.out.println("3. Seasonal Festival Special (15% OFF)");
            System.out.print("Enter your preferred discount option (1-3): ");

            DiscountStrategy strategy = new NoDiscount();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 2) strategy = new MemberDiscount();
                else if (choice == 3) strategy = new SeasonalDiscount();
            }

            Invoice invoice = purchase.checkout(calculator, strategy);
            invoicePrinter.printInvoice(invoice);

            manager.recordPurchase(purchase);
            System.out.printf("\nTotal revenue: $%.2f%n", 
                    manager.calculateTotalRevenueFromHistory());

        } else {
            System.out.println("\n[NOTICE] No books were selected for purchase. Checkout has been cancelled.");
        }

        System.out.println("\n=========================================================================================");
        System.out.println("Thank you for visiting our bookstore. We wish you a great reading journey. Goodbye!");
        System.out.println("=========================================================================================");
        scanner.close();
    }
}