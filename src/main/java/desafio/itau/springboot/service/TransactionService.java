package desafio.itau.springboot.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import desafio.itau.springboot.dto.TransactionRequest;
import desafio.itau.springboot.model.Transaction;

@Service
public class TransactionService {

    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public void createTransaction(TransactionRequest request) {
        OffsetDateTime now = OffsetDateTime.now();

        if (request.getValor() == null || request.getDataHora() == null) {
            throw new IllegalArgumentException("Campos obrigatórios ausentes");
        }

        if (request.getValor() < 0) {
            throw new IllegalArgumentException("Valor negativo não permitido");
        }

        if (request.getDataHora().isAfter(now)) {
            throw new IllegalArgumentException("Data futura não permitida");
        }

        transactions.add(new Transaction(request.getValor(), request.getDataHora()));
    }

    public List<Transaction> getAllTransactions() {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime limit = now.minusSeconds(60);

        List<Transaction> recent = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getDataHora().isAfter(limit)) {
                recent.add(t);
            }
        }

        return recent;
    }

    public void clearTransactions() {
        transactions.clear();
    }
}
