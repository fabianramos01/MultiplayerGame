package model;

public abstract class MyThread implements Runnable {

	private Thread thread;
	private String text;
	private int sleep;
	private boolean stop;
	private boolean pause;

	public MyThread(String text, int sleep) {
		this.text = text;
		this.sleep = sleep;
		thread = new Thread(this);
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(sleep);
				execute();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				if (stop) {
					break;
				}
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	abstract public void execute();

	public void start() {
		thread.start();
	}

	public synchronized void stop() {
		stop = true;
		notify();
	}

	public synchronized void pause() {
		pause = true;
		notify();
	}

	public synchronized void resume() {
		pause = false;
		notify();
	}

	public Thread getThread() {
		return thread;
	}

	public String getText() {
		return text;
	}
	
	public int getSleep() {
		return sleep;
	}

	public boolean isPause() {
		return pause;
	}

	public boolean isStop() {
		return stop;
	}
}