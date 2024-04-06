import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementCSVParserTest {
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine(){
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY,30),-50,"Tesco");

        final double tolerance = 0.0;

        Assert.assertEquals(expected.getDate(),result.getDate());
        Assert.assertEquals(expected.getAmount(),result.getAmount(),tolerance);
        Assert.assertEquals(expected.getDescription(),result.getDescription());
    }


    @Test
    public void shouldParseCorrectLines(){
        final List<String> lines = List.of("30-01-2017,-100,Deliveroo","30-01-2017,-50,Tesco");

        final List<BankTransaction> transactions = statementParser.parseLinesFrom(lines);

        Assert.assertEquals(transactions.size(),2);

        Assert.assertEquals(transactions.get(0),new BankTransaction(LocalDate.of(2017,Month.JANUARY,30),-100, "Deliveroo" ));
        Assert.assertEquals(transactions.get(1),new BankTransaction(LocalDate.of(2017,Month.JANUARY,30),-50, "Tesco" ));

    }
}
