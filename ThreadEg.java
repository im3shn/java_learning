
/**
 * ThreadEg extends Thread
 */
public class ThreadEg implements Runnable {
    int k = 0;
    synchronized public void run(){
        for (int i = 0; i < 101; i++) {
            System.out.print(i + " ");
            try {
                Thread.sleep(i+10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
        System.err.println();
    }
    
}