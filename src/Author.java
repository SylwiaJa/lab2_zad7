import java.io.IOException;

public class Author extends Thread {
    private TextFile textFile;
    public Author(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
            }
            try {
                textFile.addToFile();
                System.out.println("Author" + currentThread().getName() + " put value to file");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
