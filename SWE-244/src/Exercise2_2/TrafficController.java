package Exercise2_2;

public class TrafficController {
    int left;
    int  right;
    public TrafficController(){
        left=0;
        right=0;
    }
    public synchronized void enterLeft() throws InterruptedException {
        while(right>0){
            wait();
        }
        left++;
    }
    public synchronized void enterRight() throws InterruptedException {
        while(left>0){
            wait();
        }
        right++;
    }
    public synchronized void leaveLeft() {
        if(--right==0){
            notifyAll();
        }
    }
    public synchronized void leaveRight() {
        if(--left==0){
            notifyAll();
        }
    }

}