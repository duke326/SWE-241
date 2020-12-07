package Exercise2_2;

public class TrafficController {
    int left;
    int  right;
    public TrafficController(){
        left=0;
        right=0;
    }
    //red car enter the bridge from the left
    public synchronized void enterLeft() throws InterruptedException {
        while(right>0){
            wait();//if there is blue car in the bridge, red car should wait for the resources of bridge
        }
        left++;
    }
    //blue car enter the bridge from the right
    public synchronized void enterRight() throws InterruptedException {
        while(left>0){
            wait();
        }
        right++;
    }
    //blue car left the bridge from the left
    public synchronized void leaveLeft() {
        if(--right==0){
            notifyAll();
        }
    }
    //read car left the bridge from the right
    public synchronized void leaveRight() {
        if(--left==0){
            notifyAll();
        }
    }

}