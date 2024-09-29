/**
 * ThreadCommunication
 */
public class ThreadCommunication {

    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);
    }
}

class Q{
    int num;
    boolean valueSet = false;

    public synchronized void put(int num) {
        while(valueSet){
            try{
                wait();
            }catch (Exception e) {
                System.err.println(e);
            }
        }
        this.num = num;
        System.out.println("put = " + num);
        this.valueSet = true;
        notify();
    }

    public synchronized void get(){
        while(!valueSet){
            try{
                wait();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        System.out.println("get = " + this.num + "\n");
        this.valueSet = false;
        notify();
    }

}

class Producer implements Runnable{
    Q q;

    public Producer(Q q){
        this.q = q;
        Thread t1 = new Thread(this, "Producer");
        t1.start();
    }

	@Override
	public void run() {
        for ( int i = 0; i < 1_000_000; i++ ) {
            q.put(i);
             try{
                 Thread.sleep(10);
             }catch (Exception e){
                 System.out.println("Producer Thread Error " + e);
             }
        }
	}

}

class Consumer implements Runnable {

    Q q;

    public Consumer(Q q){
        this.q = q;
        Thread t1 = new Thread(this, "Consumer");
        t1.start();
    }

	@Override
	public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            q.get();
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println("Consumer Thread Error " + e);
            }
        }
	}

}
