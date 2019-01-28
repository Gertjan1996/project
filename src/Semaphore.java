public class Semaphore {
    private int max;
    private int value;

    Semaphore(int value){
        this.max = value;
        this.value = value;
    }

    synchronized void p() throws InterruptedException{
        while(this.value <= 0 ) wait();
        this.value--;
        this.notify();
    }

    synchronized void v() throws InterruptedException{
        while (this.value >= max) wait ();
        this.value++;
        this.notify();
    }
}