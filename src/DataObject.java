public class DataObject {
    public String TEMP;
    public String DEWP;
    public String STP;
    public String SLP;
    public String VISIB;
    public String WDSP;
    public String PRCP;
    public String SNDP;
    public String FRSHTT;
    public String CLDC;
    public String WNDDIR;

    public DataObject(String[] arrOfStr) {
        //System.out.println("arr0: " + arrOfStr[0] + " arr1: " + arrOfStr[1] + " arr2: " + arrOfStr[2] + " arr3: " + arrOfStr[3] + " arr4: " + arrOfStr[4]);
        this.TEMP = arrOfStr[3];
        this.DEWP = arrOfStr[4];
        this.STP = arrOfStr[5];
        this.SLP = arrOfStr[6];
        this.VISIB = arrOfStr[7];
        this.WDSP = arrOfStr[8];
        this.PRCP = arrOfStr[9];
        this.SNDP = arrOfStr[10];
        this.FRSHTT = arrOfStr[11];
        this.CLDC = arrOfStr[12];
        this.WNDDIR = arrOfStr[13];
    }

    public void printDataObject() {
        System.out.println(TEMP);
        System.out.println(DEWP);
        System.out.println(STP);
        System.out.println(SLP);
        System.out.println(VISIB);
        System.out.println(WDSP);
        System.out.println(PRCP);
        System.out.println(SNDP);
        System.out.println(FRSHTT);
        System.out.println(CLDC);
        System.out.println(WNDDIR);
    }
}