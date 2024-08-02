package mirkoabozzi.entities;

import mirkoabozzi.enums.Periodicita;

import java.time.LocalDate;

public class Riviste extends Catalogo {
    private Periodicita periodicita;

    public Riviste(int isbn, String titolo, LocalDate annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "periodicita=" + periodicita +
                '}';
    }
}
