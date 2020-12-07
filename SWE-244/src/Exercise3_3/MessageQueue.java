package Exercise3_3;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private static int n_ids;
	static Consumer []c1;
	static Producer []p1;
    public static void main(String[] args) {
	LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
	int consumersNum=Integer.valueOf(args[0]);
	int producersNum=Integer.valueOf(args[1]);
	c1=new Consumer[consumersNum];
	p1=new Producer[producersNum];

	for(int i=0;i<consumersNum;i++){

		c1[i]=new Consumer(queue, n_ids++);
		new Thread(c1[i]).start();
	}
	for(int i=0;i<producersNum;i++){
		p1[i]=new Producer(queue, n_ids++);
		new Thread(p1[i]).start();
	}


	try {
	    Thread.sleep(10000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	for(int i=0;i<producersNum;i++){
		p1[i].stop();
	}


    }
}
