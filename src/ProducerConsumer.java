import java.util.ArrayList;
import java.util.*;

public class ProducerConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Q queue = new Q(3);
		List<Thread> threads = new ArrayList<>();
		for(int i = 0; i < 6;i++) {
			if(i >= 3) {
				threads.add(new Thread(new Producer(queue,i)));
			}else {
				threads.add(new Thread(new Consumer(queue,i)));
			}
		}
		
		for(Thread t : threads) {
			t.start();
		}
		

	}

}

class Q{
	private final int limit;
	private Queue<Integer> q;
	public Q(int limit) {
		this.limit = limit;
		q = new LinkedList<>();
	}
	
	public synchronized void put(Integer num) {
		while(q.size() == limit) {
			try {
				System.out.println("The producer is waiting...");
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(q.size() == 0) {
			notifyAll();
			System.out.println("notify other threads");

		}
		q.offer(num);
	}
	
	public synchronized Integer take() {
		while(q.size() == 0) {
			try {
				System.out.println("The consumer is waiting...");
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(q.size() == limit) {
			notifyAll();
			System.out.println("notify other threads");
		}
		
		return q.poll();
	}
	
}

class Producer implements Runnable{
	private Q q;
	private int counter;
	public Producer(Q q,int counter) {
		this.q = q;
		this.counter = counter;
	}
	@Override
	public void run() {
		System.out.println("Producer put: " + counter);
		q.put(counter);
	}
	
}

class Consumer implements Runnable{
	Q q;
	int counter;
	public Consumer(Q q,int counter) {
		this.q = q;
		this.counter = counter;
	}
	
	@Override
	public void run() {
		System.out.println("Consumer" + counter + " takes " + q.take());
	}
}
