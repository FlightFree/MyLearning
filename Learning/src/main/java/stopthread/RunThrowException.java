package stopthread;

/**
 * run 无法抛出checked Exception，只能try/catch
 * @author ：kailu
 */
public class RunThrowException {
    public void aVoid() throws Exception {
        throw new Exception();
    }
    public static void main(String args[]){
        new Thread(
                new Runnable() {
                    @Override
                    //顶层方法无异常抛出处理，在重写时无法进行修改对异常的处理，因此
                    //只能try/catch
                   //错误写法 public void run() throws Exception  {
                    public void run() {
                        try {
                            throw  new Exception();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

}
