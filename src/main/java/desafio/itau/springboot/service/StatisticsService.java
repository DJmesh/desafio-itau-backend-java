package desafio.itau.springboot.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import desafio.itau.springboot.dto.StatisticsResponse;
import desafio.itau.springboot.model.Transaction;

@Service
public class StatisticsService {

    private final TransactionService transactionService;

    public StatisticsService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public StatisticsResponse getStatistics() {
        List<Transaction> last60 = transactionService.getAllTransactions();

        DoubleSummaryStatistics stats = last60.stream()
                .collect(Collectors.summarizingDouble(Transaction::getValor));

        return new StatisticsResponse(stats);
    }
}
