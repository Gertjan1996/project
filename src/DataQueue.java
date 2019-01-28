import java.util.LinkedList;

class DataQueue {
    private LinkedList<String> dataList = new LinkedList<>();

    DataQueue() {

    }

    synchronized void addToList(String data) {
        dataList.addFirst(data);
    }

    synchronized String popFromList() {
        return dataList.pollLast();
    }

    synchronized boolean listEmpty() {
        return dataList.isEmpty();
    }

    synchronized String getLastData() {
        return dataList.getLast();
    }

}