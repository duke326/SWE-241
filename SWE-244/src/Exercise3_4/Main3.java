package Exercise3_4;

import java.util.concurrent.Semaphore;

public class Main3 {
   static Semaphore se;
   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    //HighLevelDisplay d=new JDisplay2();
    private static void addProc(Exercise3_4.HighLevelDisplay d) throws InterruptedException {
        for(int i=0;i<20;i++){
            se.acquire();
            d.addRow("AAAAAAAAAAAAA"+i);
            d.addRow("BBBBBBBBBBBBBB"+i);
            se.release();
            nap(500);

        }

	// Add a sequence of addRow operations with short random naps.

   }

    private static void deleteProc(Exercise3_4.HighLevelDisplay d) throws InterruptedException {
        for(int i=0;i<20;i++){
            se.acquire();
            d.deleteRow(0);
            se.release();
            nap(1000);
           // d.deleteRow(0);
        }

	// Add a sequence of deletions of row 0 with short random naps.
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();
	se=new Semaphore(1);

	new Thread () {
	    public void run() {

            try {
                addProc(d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
	}.start();


	new Thread () {
	    public void run() {
            try {
                deleteProc(d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

	    }
	}.start();

    }
}