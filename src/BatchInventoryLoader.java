import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

// Handles memory-efficient batch loading for large datasets
public class BatchInventoryLoader {
    private final BookFactory bookFactory;

    public BatchInventoryLoader(BookFactory bookFactory) {
        this.bookFactory = bookFactory;
    }

    public void processLargeCatalogFile(Path filePath, Consumer<Book> processor) throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.skip(1) 
                 .filter(line -> line != null && !line.isBlank())
                 .map(this::parseCsvLineToBook)
                 .filter(Optional::isPresent)
                 .map(Optional::get)
                 .forEach(processor);
        }
    }

    private Optional<Book> parseCsvLineToBook(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 5) return Optional.empty();

        try {
            String type = parts[0].trim();
            String title = parts[1].trim();
            String author = parts[2].trim();
            double price = Double.parseDouble(parts[3].trim());
            double extraDetail = Double.parseDouble(parts[4].trim());

            return Optional.of(BookFactory.createBook(type, title, author, price, extraDetail));
        } catch (NumberFormatException e) {
            return Optional.empty(); 
        }
    }
}