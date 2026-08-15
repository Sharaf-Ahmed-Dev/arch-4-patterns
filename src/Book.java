public abstract class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        setPrice(price);
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Book price cannot be negative.");
        }
        this.price = price;
    }

    public abstract String getFormatDetails();

    public void displayBook(int index) {
        System.out.printf("%-5d | %-37s | %-20s | $%-8.2f | %-22s\n", 
                index, title, author, price, getFormatDetails());
    }
}