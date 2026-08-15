public class BookFactory {
    public static Book createBook(String type, String title, String author, double price, double extraParam) {
        if (type == null) {
            throw new IllegalArgumentException("Book type cannot be null.");
        }
        if (type.equalsIgnoreCase("PRINTED")) {
            return new PrintedBook(title, author, price, extraParam);
        } else if (type.equalsIgnoreCase("EBOOK")) {
            return new EBook(title, author, price, extraParam);
        }
        throw new IllegalArgumentException("Invalid book type provided: " + type);
    }
}