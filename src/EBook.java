public class EBook extends Book {
    private double fileSizeMb;

    public EBook(String title, String author, double price, double fileSizeMb) {
        super(title, author, price);
        this.fileSizeMb = fileSizeMb;
    }

    public double getFileSizeMb() { return fileSizeMb; }

    @Override
    public String getFormatDetails() {
        return "E-Book (" + fileSizeMb + " MB)";
    }
}