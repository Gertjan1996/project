import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
                //System.out.println("ParserWorker: " + data);

                String[] arrOfStr = data.split(";");
                String[] arrOfDate = arrOfStr[1].split("-");
                String[] arrOfTime = arrOfStr[2].split(":");

                Map<String, DataObject> mapOfData = new HashMap<>();

                //printArray(arrOfStr);
                //printArray(arrOfDate);
                //printArray(arrOfDay);

                DataObject dataObject = new DataObject(arrOfStr);
                //System.out.println("3: " + arrOfStr[3] + " 4: " + arrOfStr[4] + " 5: " + arrOfStr[5] + " 6: " + arrOfStr[6] + " 7: " + arrOfStr[7] + " 8: " + arrOfStr[8] + " 9: " + arrOfStr[9] + " 10: " + arrOfStr[10] + " 11: " + arrOfStr[11] + " 12: " + arrOfStr[12] + " 13: " + arrOfStr[13]);
                //dataObject.printDataObject();

                // key = time, value = dataObject
                mapOfData.put(arrOfStr[2], dataObject);

                // map to json
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(mapOfData);

                File fileDir = new File("C:\\data-directory\\" + arrOfStr[0] + "\\" + arrOfDate[0] + "\\" + arrOfDate[1] + "\\" + arrOfDate[2]);

                // creates (sub)directories of not existing yet
                writeDir(fileDir);

                FileWriter writer = null;
                FileWriter writer2 = null;

                String fileName = arrOfTime[0] + ".json";
                System.out.println(fileName);

                try {
                    writer = new FileWriter(fileDir + "\\" + fileName, true);
                    writer.write(jsonString);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
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