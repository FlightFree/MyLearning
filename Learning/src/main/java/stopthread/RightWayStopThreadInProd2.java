package stopthread;

/**
 * 最佳实践2：
 *在catch子语句中调用Thread.currentThread().intertupt()来恢复设置中断状态，以便于在
 * 后续的执行中，依然能够检查到刚才发生的中断，回到刚才RightWayStopThreadInProd补上
 * 中断，让它跳出
 * @author ：kailu
 */
public class RightWayStopThreadInProd2 implements Runnable {


        @Override
        public void run() {
            while (true) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted,程序运行结束！");
                    break;
                }
                reInMethod();
            }
        }

        private void reInMethod() {
            try {
                System.out.println("go");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    public static void  main (String args[]) throws InterruptedException {
        Thread thread=new Thread( new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
