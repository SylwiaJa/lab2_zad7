public class Writer extends Thread {
    private TextFile textFile;
    public Writer(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public void run() {
        String txt;
        while (true){
            txt=textFile.readFromFile();
            if(txt.equals("0")) System.exit(0);
            System.out.println("Writer" + currentThread().getName()+" read value: " +txt);
          }
    }
}
