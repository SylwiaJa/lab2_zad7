/*Zdefiniuj synchronizowaną i skoordynowaną pracę dwóch wątków "Author" i "Writer"
       odczytującą i zapisującą zawartość pliku tekstowego.
       Autor wpisuje w pliku jedną linię tekstu i czeka, aż Writer ją odczyta zanim doda kolejną.
       Dane wpisywane do pliku należy wpisywać z klawiatury.
       Do koordynacji pracy wątków wykorzystaj obiekty Condition.
       */

public class Main {
    public static void main(String[] args) {
        TextFile textFile = new TextFile();
        new Author(textFile).start();
        new Writer(textFile).start();
        System.out.println("Write line,\"0\" mean end");
    }
}
