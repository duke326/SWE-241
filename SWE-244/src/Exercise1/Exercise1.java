package Exercise1;

import java.util.Scanner;

public class Exercise1 {
    static MultiThread thread;

    public static void main(String[] args) {
        boolean flag=true;

        while (flag) {
            System.out.println("Select your option: a - Create a new thread, b - Stop a given thread, c - Stop all threads and exit this program");
            Scanner sc=new Scanner(System.in);
            String chooice=sc.nextLine();
            if(chooice.equals("a")){
                thread=new MultiThread();
                thread.start();
            }
            else if (chooice.startsWith("b")){
                //ThreadMXBean mxBean= ManagementFactory.getThreadMXBean();

                int id=Integer.valueOf(chooice.substring(2));
                Thread s=findThread(id);
                //interrupt is safe than stop method, it give a interrupt singnal to the threat
                s.interrupt();
            }
            else if (chooice.equals("c")){
                stopAll();
                flag=false;
            }

        }

    }

    private static void stopAll() {
        ThreadGroup group=Thread.currentThread().getThreadGroup();
        while(group!=null){
            Thread[] threads=new Thread[(int) (group.activeCount()*1.2)];
            int count=group.enumerate(threads, true);
            for(int i=0;i<count;i++){
                threads[i].interrupt();

            }
            group=group.getParent();
        }
    }

    public static Thread findThread(long threadId) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while(group != null) {
            Thread[] threads = new Thread[(int)(group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for(int i = 0; i < count; i++) {
                if(threadId == threads[i].getId()) {
                    threads[i].interrupt();
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }
}
