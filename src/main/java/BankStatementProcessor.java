import java.time.Month;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount(){
        return summarizeTransactions((acc,bankTransaction)->acc+bankTransaction.getAmount() );
    }

    public double calculateTotalInMonth(final Month month){
        return summarizeTransactions((acc,bankTransaction)->{
            if(bankTransaction.getDate().getMonth() == month) {
                return acc + bankTransaction.getAmount();
            }
            return acc;
        });
    }

    public double calculateTotalForCategory(final String category ){
        return summarizeTransactions((acc,bankTransaction)->{
            if(bankTransaction.getDescription().equals(category)){
                return acc+bankTransaction.getAmount();
            }
            return acc;
        });
    }

    public SummaryStatistics summarizeTransactions(){
        final DoubleSummaryStatistics statistics = bankTransactions.stream().mapToDouble(BankTransaction::getAmount).summaryStatistics();

        return new SummaryStatistics(
                statistics.getSum(),
                statistics.getMax(),
                statistics.getMin(),
                statistics.getAverage()
        );

    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer){
        double result = 0;
        for(final BankTransaction bankTransaction : bankTransactions ){
            result = bankTransactionSummarizer.summarize(result, bankTransaction );
        }
        return result;
    }

    public List<BankTransaction> findTransactions(Predicate<BankTransaction> predicate){
        return bankTransactions.stream().filter(predicate).toList();
    }

    public List<BankTransaction> findTransactionGreatThanEqual(final int amount){
        return findTransactions((t)->t.getAmount()>=amount);
    }

}
