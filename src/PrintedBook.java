public class PrintedBook extends Book {
    private double weightInKg;

    public PrintedBook(String title, String author, double price, double weightInKg) {
        super(title, author, price);
        this.weightInKg = weightInKg;
    }

    public double getWeightInKg() { return weightInKg; }

    @Override
    public String getFormatDetails() {
        return "Printed (" + weightInKg + " kg)";
    }
}