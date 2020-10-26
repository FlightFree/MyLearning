package stopthread;

/**
 * 最佳实践：
 * catch了InterruptedException之后的优先选择：
 * 在方法签名中抛出异常
 * 那么在run（）就会强制try/catch
 *seelp会清除中断信号，所以try/catch后，程序并不会停止
 * @author ：kailu
 */
public class RightWayStopThreadInProd implements Runnable {

    /*
        @Override
        public void run() {
            while (true&&!Thread.currentThread().isInterrupted()) {
                throwInMethod();

            }
        }
    错误写法，无法正常中断线程
        private void throwInMethod() {
            try {
                System.out.println("go");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    @Override
    public void run() {
        while (true&&!Thread.currentThread().isInterrupted()) {
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                //可进行其它操作：
                //保存日志，停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }

        }
    }
    private void throwInMethod() throws InterruptedException {
            System.out.println("go");
            Thread.sleep(2000);
    }
    public static void  main (String args[]) throws InterruptedException {
        Thread thread=new Thread( new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
