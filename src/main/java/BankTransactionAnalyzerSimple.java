import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/bank-data-simple.csv";
    public static void main(String[] args) throws IOException {
        final Path path = Paths.get(RESOURCES);
        final List<String> lines = Files.readAllLines(path);
        double total = 0;
        for(final String line : lines ){
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println("The total for all transactions is " + total);
    }
}
