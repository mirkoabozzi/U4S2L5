package mirkoabozzi;

import mirkoabozzi.entities.Libro;
import mirkoabozzi.entities.Rivista;
import mirkoabozzi.enums.Periodicita;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Libro> listaLibri = new ArrayList<>();

        Libro libro1 = new Libro(1234, "Il Signore Degli Anelli", "1978", 530, "Tolkien", "romanzo");
        Libro libro2 = new Libro(5678, "Harry Potter e la pietra filosofale", "1997", 344, "Rowling", "Romanzo");
        Libro libro3 = new Libro(8910, "Harry Potter e la camera dei segreti", "1998", 375, "Rowling", "Romanzo");
        Libro libro4 = new Libro(1112, "Le Cronache di Narnia", "2008", 1168, "Lewis", "Fantasy");
        Rivista rivista1 = new Rivista(1213, "La Nuova Sardegna", "1881", 10, Periodicita.SETTIMANALE);
        Rivista rivista2 = new Rivista(1415, "Unione Sarda", "1889", 10, Periodicita.SETTIMANALE);
        Rivista rivista3 = new Rivista(1617, "La Gazzetta Dello Sport", "1896", 10, Periodicita.SETTIMANALE);
        Rivista rivista4 = new Rivista(1819, "Tutto Sport", "1946", 10, Periodicita.MENSILE);
        listaLibri.add(libro1);
        listaLibri.add(libro2);
        listaLibri.add(libro3);
        listaLibri.add(libro4);

        System.out.println("Cosa vuoi fare?");
        System.out.println("1. Aggiungi un libro");
        System.out.println("2. Rimuovi un libro");
        System.out.println("3. Cerca un libro tramite ISBN");
        System.out.println("4. Cerca un libro tramite Anno");
        System.out.println("5. Cerca un libro tramite Autore");


        String scelta = scanner.nextLine();
        switch (scelta) {
            case "1":
                aggiungiLibro(listaLibri);
                break;
            case "2":
                rimuoviLibro(listaLibri);
                break;
            case "3":
                cercaISBN(listaLibri);
                break;
            case "4":
                cercaAnno(listaLibri);
                break;
            case "5":
                cercaAutore(listaLibri);
                break;
            default:
                break;

        }


    }

    public static void aggiungiLibro(List<Libro> listaLibri) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aggiungi un libro");
        System.out.println("Inserisci ISBN");
        int isbn = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci Titolo");
        String titolo = scanner.nextLine();
        System.out.println("Inserisci anno pubblicazione");
        String annoPubblicazione = scanner.nextLine();
        System.out.println("Inserisci numero di pagine");
        int numeroPagine = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Inserisci Autore");
        String autore = scanner.nextLine();
        System.out.println("Inserisci Genere");
        String genere = scanner.nextLine();
        Libro libro = new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);

        listaLibri.add(libro);

        System.out.println("Libro aggiunto");
        scanner.close();
    }

    public static void rimuoviLibro(List<Libro> listaLibri) {
        if (listaLibri.isEmpty()) System.out.println("La lista di libri è vuota!");
        else {

            listaLibri.forEach(System.out::println);
            System.out.println("Che libro vuoi rimuovere? scegli un ISBN valido tra i libri sopra riportati");
            Scanner scanner = new Scanner(System.in);
            int isbn = scanner.nextInt();
            scanner.nextLine();
            listaLibri.removeIf(book -> book.getIsbn() == isbn);
            System.out.println("libro rimosso");
            listaLibri.forEach(System.out::println);

            scanner.close();
        }

    }

    public static void cercaISBN(List<Libro> listaLibri) {
        listaLibri.forEach(System.out::println);
        System.out.println("Inserisci un ISBN valido tra i libri sopra riportati");
        Scanner scanner = new Scanner(System.in);
        int isbn = scanner.nextInt();
        scanner.nextLine();
        listaLibri.stream().filter(book -> book.getIsbn() == isbn).forEach(System.out::println);
    }

    public static void cercaAnno(List<Libro> listaLibri) {
        listaLibri.forEach(System.out::println);
        System.out.println("Inserisci un anno valido tra i libri sopra riportati");
        Scanner scanner = new Scanner(System.in);
        String anno = scanner.nextLine();
        listaLibri.stream().filter(book -> Objects.equals(book.getAnnoPubblicazione(), anno)).forEach(System.out::println);
    }

    public static void cercaAutore(List<Libro> listaLibri) {
        listaLibri.forEach(System.out::println);
        System.out.println("Inserisci un Autore valido tra i libri sopra riportati");
        Scanner scanner = new Scanner(System.in);
        String autore = scanner.nextLine();
        listaLibri.stream().filter(book -> Objects.equals(book.getAutore(), autore)).forEach(System.out::println);
    }

}
