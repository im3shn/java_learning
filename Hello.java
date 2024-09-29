
public class Hello{

    public static void main(String[] args) throws InterruptedException {
        ThreadEg o1 = new ThreadEg();
        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o1);
        t1.start();
        t2.start();
        t1.join();
        t2.join(); 
        System.out.println(o1.c.c);
    }
}

class Counter{
    int c;
    public void increment(){
        c++;
    }
}




class ThreadEg implements Runnable{

    Counter c = new Counter();


	@Override
	public synchronized void run() {
        for (int i = 0; i < 100000; i++) {
            c.increment();
        }
	}
    
}
