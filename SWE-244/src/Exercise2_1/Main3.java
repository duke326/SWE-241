package Exercise2_1;

public class Main3 {

   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
    //HighLevelDisplay d=new JDisplay2();
    private static void addProc(HighLevelDisplay d) {
        for(int i=0;i<20;i++){
            d.addRow("AAAAAAAAAAAAA"+i);
            d.addRow("BBBBBBBBBBBBBB"+i);
            nap(500);

        }

	// Add a sequence of addRow operations with short random naps.

   }

    private static void deleteProc(HighLevelDisplay d) {
        for(int i=0;i<20;i++){
            d.deleteRow(0);
            ;
            nap(1000);
           // d.deleteRow(0);
        }

	// Add a sequence of deletions of row 0 with short random naps.
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();

	new Thread () {
	    public void run() {
		addProc(d);
	    }
	}.start();


	new Thread () {
	    public void run() {
		deleteProc(d);
	    }
	}.start();

    }
}