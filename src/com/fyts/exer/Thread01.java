package com.fyts.exer;

/**
 * *
 * @author bky
 *
 * 2016年11月22日上午10:52:51
 */

class shareDate{
	
	private int number = 0;
	
	public synchronized void increment() throws Exception{
		while(number != 0){
			this.wait();
		}
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		this.notify();
	}
	
	public synchronized void decrement() throws Exception{
		while(number == 0){
			this.wait();
		}
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		this.notify();
	}
	
}

public class Thread01 {

	public static void main(String[] args) {
		
		final shareDate sd = new shareDate();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i <= 10; i++){
					try {
						//Thread.sleep(200);
						sd.increment();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		},"AA").start();;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i <= 10; i++){
					try {
						//Thread.sleep(300);
						sd.decrement();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		},"BB").start();;
		
	}
}
