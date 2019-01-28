import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int Port = 7790;
    private static final int maxNrOfConnections=1;

    static Semaphore Limiter = new Semaphore(maxNrOfConnections);
    static DataQueue dataQueue = new DataQueue();

    public static void main(String[] args) {
        Socket connection;
        try {
            Thread ParserWorker = new Thread(new ParserWorker());
            ParserWorker.start();

            ServerSocket receiver = new ServerSocket(Port);
            while (true) {
                connection = receiver.accept();
                Thread dataWorker = new Thread(new DataWorker(connection));
                dataWorker.start();
            }
        }
        catch (java.io.IOException ignored) { }
    }
}