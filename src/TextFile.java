import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TextFile {
    Scanner sc = new Scanner(System.in);
    String txt = null;
    File fileName = new File("txt.txt");
    boolean ready = false;
    Lock lock = new ReentrantLock();
    Condition txtWritten = lock.newCondition();
    Condition txtSupplied = lock.newCondition();

    public void addToFile() throws IOException {
        lock.lock();
        try {
            while (ready) {
                txtWritten.await();
            }
            try (FileWriter f = new FileWriter(fileName, true);
                 BufferedWriter out = new BufferedWriter(f)) {
                out.write(sc.nextLine());
                out.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ready = true;
            txtSupplied.signal();
        } catch (InterruptedException e) {
        } finally {
            if (((ReentrantLock) lock).isHeldByCurrentThread()) lock.unlock();
        }
    }

    public String readFromFile() {
        lock.lock();
        try {
            while (!ready) txtSupplied.await();
            try (FileInputStream f = new FileInputStream(fileName);
                 DataInputStream in = new DataInputStream(f);
                 BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
                String strLine;
                while ((strLine = r.readLine()) != null) {
                    txt = strLine;
                }
            } catch (IOException e) {
            }
            ready = false;
            txtWritten.signal();
            return txt;
        } catch (InterruptedException e) {
            return null;
        } finally {
            if (((ReentrantLock) lock).isHeldByCurrentThread()) lock.unlock();
        }
    }
}


