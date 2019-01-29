import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int Port = 7790;
    private static final int maxNrOfConnections=100;

    static Semaphore Limiter = new Semaphore(maxNrOfConnections);
    static DataQueue dataQueue = new DataQueue();

    public static void main(String[] args) {
        Socket connection;
        try {
            Thread ParserWorker = new Thread(new ParserWorker(1));
            System.out.println("Thread 1");
            ParserWorker.start();

            Thread ParserWorker2 = new Thread(new ParserWorker(2));
            System.out.println("Thread 2");
            ParserWorker2.start();

            Thread ParserWorker3 = new Thread(new ParserWorker(3));
            System.out.println("Thread 3");
            ParserWorker3.start();

            Thread ParserWorker4 = new Thread(new ParserWorker(4));
            System.out.println("Thread 4");
            ParserWorker4.start();

            Thread ParserWorker5 = new Thread(new ParserWorker(5));
            System.out.println("Thread 5");
            ParserWorker5.start();

            Thread ParserWorker6 = new Thread(new ParserWorker(6));
            System.out.println("Thread 6");
            ParserWorker6.start();

            Thread ParserWorker7 = new Thread(new ParserWorker(7));
            System.out.println("Thread 7");
            ParserWorker7.start();

            ServerSocket receiver = new ServerSocket(Port);

            //noinspection InfiniteLoopStatement
            while (true) {
                connection = receiver.accept();
                Thread dataWorker = new Thread(new DataWorker(connection));
                dataWorker.start();
            }
        }
        catch (java.io.IOException ignored) { }
    }
}