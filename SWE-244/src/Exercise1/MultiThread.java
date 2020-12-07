package Exercise1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiThread extends Thread{

    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                break;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Hello World! I'm thread "+Thread.currentThread().getId()+ " The time is "+df.format(new Date()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //System.out.println("Thread has interrepted ");
                return;
            }
        }



    }

}
