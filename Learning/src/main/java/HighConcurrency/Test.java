package HighConcurrency;

/**
 * @author ：kailu
 */
public class Test extends Thread{
    static  boolean flag=false;

    @Override
    public void run() {
        while (!flag);
    }

    public static void main(String[] args) {
        Test t=new Test();
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag=true;
    }
}
