package mirkoabozzi;

import mirkoabozzi.entities.Catalogo;
import mirkoabozzi.entities.Libro;
import mirkoabozzi.entities.Rivista;
import mirkoabozzi.enums.Periodicita;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        List<Catalogo> listaCatalogo = new ArrayList<>();

        Libro libro1 = new Libro(1234, "Il Signore Degli Anelli", 1978, 530, "Tolkien", "romanzo");
        Libro libro2 = new Libro(5678, "Harry Potter e la pietra filosofale", 1997, 344, "Rowling", "Romanzo");
        Libro libro3 = new Libro(8910, "Harry Potter e la camera dei segreti", 1998, 375, "Rowling", "Romanzo");
        Libro libro4 = new Libro(1112, "Le Cronache di Narnia", 2008, 1168, "Lewis", "Fantasy");
        Rivista rivista1 = new Rivista(1213, "La Nuova Sardegna", 1881, 10, Periodicita.SETTIMANALE);
        Rivista rivista2 = new Rivista(1415, "Unione Sarda", 1889, 10, Periodicita.SETTIMANALE);
        Rivista rivista3 = new Rivista(1617, "La Gazzetta Dello Sport", 1896, 10, Periodicita.SETTIMANALE);
        Rivista rivista4 = new Rivista(1819, "Tutto Sport", 1946, 10, Periodicita.MENSILE);
        listaCatalogo.add(libro1);
        listaCatalogo.add(libro2);
        listaCatalogo.add(libro3);
        listaCatalogo.add(libro4);

        listaCatalogo.add(rivista1);
        listaCatalogo.add(rivista2);
        listaCatalogo.add(rivista3);
        listaCatalogo.add(rivista4);
        stop:
        while (true) {

            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Aggiungi un libro");
            System.out.println("2. Rimuovi un libro");
            System.out.println("3. Cerca un libro tramite ISBN");
            System.out.println("4. Cerca un libro tramite Anno");
            System.out.println("5. Cerca un libro tramite Autore");
            System.out.println("6. Salva archivio su file");
            System.out.println("7. Leggi archivio da file");
            System.out.println("8. Chiudi programma");

            String scelta = "";
            try {
                scelta = scanner.nextLine();
            } catch (IllegalStateException e) {
                System.out.println("Scelta non valida " + e.getMessage());
            }
            switch (scelta) {
                case "1":
                    aggiungiLibro(listaCatalogo);
                    break;
                case "2":
                    rimuoviLibro(listaCatalogo);
                    break;
                case "3":
                    cercaISBN(listaCatalogo);
                    break;
                case "4":
                    cercaAnno(listaCatalogo);
                    break;
                case "5":
                    cercaAutore(listaCatalogo);
                    break;
                case "6":
                    salvaArchivio(listaCatalogo);
                    break;
                case "7":
                    leggiArchivio();
                    break;
                case "8":
                    System.out.println("Grazie, buona giornata!");
                    break stop;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }
    }

    public static void aggiungiLibro(List<Catalogo> listaCatalogo) {
        try {
            System.out.println("Aggiungi un libro");
            System.out.println("Inserisci ISBN");
            int isbn = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Inserisci Titolo");
            String titolo = scanner.nextLine();
            System.out.println("Inserisci anno pubblicazione");
            int annoPubblicazione = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Inserisci numero di pagine");
            int numeroPagine = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Inserisci Autore");
            String autore = scanner.nextLine();
            System.out.println("Inserisci Genere");
            String genere = scanner.nextLine();
            Libro libro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
            listaCatalogo.add(libro);
            System.out.println("Libro aggiunto: " + libro);
            listaCatalogo.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Input non valido inserisci i dati corretti");
            scanner.nextLine();
        }

    }

    public static void rimuoviLibro(List<Catalogo> listaCatalogo) {
        if (listaCatalogo.isEmpty()) System.out.println("La lista di libri è vuota!");
        else {
            try {
                listaCatalogo.forEach(System.out::println);
                System.out.println("Che libro vuoi rimuovere? scegli un ISBN valido tra i libri sopra riportati");
                int isbn = scanner.nextInt();
                scanner.nextLine();
                boolean libroEsiste = listaCatalogo.stream().anyMatch(book -> book.getIsbn() == isbn);
                if (libroEsiste) {
                    listaCatalogo.removeIf(book -> book.getIsbn() == isbn);
                    System.out.println("libro rimosso");
                } else {
                    System.out.println("Il libro non esiste");
                }
                listaCatalogo.forEach(System.out::println);
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un ISBN valido");
                scanner.nextLine();
            }

        }
    }

    public static void cercaISBN(List<Catalogo> listaCatalogo) {
        if (listaCatalogo.isEmpty()) System.out.println("La lista di libri è vuota!");
        else {

            try {
                listaCatalogo.forEach(System.out::println);
                System.out.println("Inserisci un ISBN valido tra i libri sopra riportati");
                int isbn = scanner.nextInt();
                scanner.nextLine();
                boolean libroEsiste = listaCatalogo.stream().anyMatch(book -> book.getIsbn() == isbn);
                if (libroEsiste) {
                    listaCatalogo.stream().filter(book -> book.getIsbn() == isbn).forEach(System.out::println);
                } else {
                    System.out.println("Il libro non esiste");
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un ISBN valido");
                scanner.nextLine();
            }
        }
    }

    public static void cercaAnno(List<Catalogo> listaCatalogo) {
        if (listaCatalogo.isEmpty()) System.out.println("La lista di libri è vuota!");
        else {
            try {
                listaCatalogo.forEach(System.out::println);
                System.out.println("Inserisci un anno valido tra i libri sopra riportati");
                int anno = scanner.nextInt();
                scanner.nextLine();
                List<Catalogo> libriAnno = listaCatalogo.stream().filter(book -> book.getAnnoPubblicazione() == anno).toList();
                if (libriAnno.isEmpty()) {
                    System.out.println("Nessun libro trovato per l'anno " + anno);
                } else {
                    libriAnno.forEach(System.out::println);
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci una data valida");
                scanner.nextLine();
            }
        }
    }

    public static void cercaAutore(List<Catalogo> listaCatalogo) {
        if (listaCatalogo.isEmpty()) System.out.println("La lista di libri è vuota!");
        else {
            listaCatalogo.forEach(System.out::println);
            System.out.println("Inserisci un autore valido tra i libri sopra riportati");
            String autore = scanner.nextLine();
            List<Libro> libriAutore = listaCatalogo.stream().filter(item -> item instanceof Libro).map(item -> (Libro) item).filter(libro -> libro.getAutore().equalsIgnoreCase(autore)).toList();
            if (libriAutore.isEmpty()) {
                System.out.println("Nessun libro trovato per l'autore " + autore);
            } else {
                libriAutore.forEach(System.out::println);
            }
        }
    }


    public static void salvaArchivio(List<Catalogo> listaCatalogo) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Catalogo catalogo : listaCatalogo) {
            stringBuilder.append(catalogo.toString()).append(System.lineSeparator());
        }
        try {
            FileUtils.writeStringToFile(new File("src/archivio.txt"), stringBuilder.toString(), StandardCharsets.UTF_8, true);
            System.out.println("Catalogo aggiornato");
        } catch (IOException e) {
            throw new RuntimeException("Errore nella scrittura del file");
        }
    }

    public static void leggiArchivio() {
        try {
            String content = FileUtils.readFileToString(new File("src/archivio.txt"), StandardCharsets.UTF_8);
            System.out.println("Contenuto dell'archivio:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }

}
