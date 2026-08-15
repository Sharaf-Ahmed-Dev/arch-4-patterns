public class ConsoleInvoicePrinter implements Printable {
    @Override
    public void printInvoice(Invoice invoice) {
        System.out.println("\n=========================================================================================");
        System.out.println("                                BOOKSTORE TRANSACTION INVOICE                            ");
        System.out.println("=========================================================================================");
        System.out.println(" Customer Name  : " + invoice.getCustomer().getName() + " (ID: " + invoice.getCustomer().getCustomerId() + ")");
        System.out.println(" Transaction ID: BK-TXN-" + invoice.getInvoiceId());
        System.out.println(" Discount Applied: " + invoice.getDiscountStrategy().getStrategyName());
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(" PURCHASED ITEMS:");
        System.out.printf(" %-5s | %-35s | %-8s | %-22s\n", "#", "Book Title", "Price ($)", "Format");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int i = 0; i < invoice.getBooks().size(); i++) {
            Book book = invoice.getBooks().get(i);
            System.out.printf(" %-5d | %-35s | $%-7.2f | %-22s\n", 
                    (i + 1), book.getTitle(), book.getPrice(), book.getFormatDetails());
        }

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf(" GRAND TOTAL AMOUNT DUE:                                                 $%.2f\n", invoice.getTotalAmount());
        System.out.println("=========================================================================================");
    }
}