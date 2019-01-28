import java.net.*;
import java.io.*;

class DataWorker implements Runnable {
    private Socket connection;

    DataWorker(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            Main.Limiter.p();

            BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String data;

            while ((data = bin.readLine()) != null) {
                Main.dataQueue.addToList(data);
                // System.out.println("DataWorker: " + Main.dataQueue.getLastData());
            }
            connection.close();
            Main.Limiter.v();
        }
        catch (IOException | InterruptedException ignored) { }
    }
}