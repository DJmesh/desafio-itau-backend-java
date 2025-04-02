package desafio.itau.springboot.model;

import java.time.OffsetDateTime;

public class Transaction {

    private final double valor;
    private final OffsetDateTime dataHora;

    public Transaction(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
