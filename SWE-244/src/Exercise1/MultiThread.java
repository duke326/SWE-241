package Exercise1;

public class MultiThread extends Thread{

    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                break;
            }
            System.out.println("Hello World! I'm thread "+Thread.currentThread().getId()+ " The time is "+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //System.out.println("Thread has interrepted ");
                return;
            }
        }



    }

}
