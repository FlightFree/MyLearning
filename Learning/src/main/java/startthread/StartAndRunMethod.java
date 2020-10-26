package startthread;

/**
 * @author ：kailu
 * 直接调用run方法和启动线程的区别
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable=()->{
            System.out.println(Thread.currentThread().getName());
        };
        //执行结果为main线程，不是线程的预期结果
        runnable.run();
        //执行结果为子线程
        new Thread(runnable).start();
    }
}
