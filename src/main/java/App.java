import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final Exporter exporter = new HTMLExporter();

        bankStatementAnalyzer.analyze("bank-data-simple.csv",bankStatementParser,exporter);
    }
}
