import java.io.File;

public class ParserWorker implements Runnable {
    public void run() {
        while (true) {
            while (Main.dataQueue.listEmpty()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            while (!Main.dataQueue.listEmpty()) {
                String data = Main.dataQueue.popFromList();
                System.out.println("ParserWorker: " + data);

                String[] arrOfStr = data.split(";");
                String[] arrOfDate = arrOfStr[1].split("-");
                String[] arrOfDay = arrOfStr[2].split(":");

                printArray(arrOfStr);
                printArray(arrOfDate);
                printArray(arrOfDay);

                DataObject dataObject = new DataObject(arrOfStr[3], arrOfStr[4], arrOfStr[5], arrOfStr[6], arrOfStr[7], arrOfStr[8], arrOfStr[9], arrOfStr[10], arrOfStr[11], arrOfStr[12], arrOfStr[13]);
                //System.out.println("DataObject: " + dataObject);

                File fileDir = new File("C:\\data-directory\\" + arrOfStr[0] + "\\" + arrOfDate[0] + "\\" + arrOfDate[1] + "\\" + arrOfDate[2]);

                // creates (sub)directories of not existing yet
                writeDir(fileDir);

                //public String TEMP;
                //public String DEWP;
                //public String STP;
                //public String SLP;
                //public String VISIB;
                //public String WDSP;
                //public String PRCP;
                //public String SNDP;
                //public String FRSHTT;
                //public String CLDC;
                //public String WNDDIR;

            }
        }
    }

    private void writeDir(File f) {
        try {
            if (f.mkdirs()) {
                System.out.println("Directory Created");
            } else {
                System.out.println("Directory Already Exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printArray(String[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
        }
    }
}