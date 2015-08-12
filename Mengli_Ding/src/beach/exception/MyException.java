package beach.exception;

/**
 * Created by mlding on 8/12/15.
 */
public class MyException extends Exception {
    public MyException() {
        System.out.println("MyException occurs,the money must greater than current money.");
    }
}
