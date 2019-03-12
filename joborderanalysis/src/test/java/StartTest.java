import org.junit.Test;

public class StartTest {

    @Test
    public void run() {
        String [] args ={"HistoryInstallOrders","D:\\data\\orders\\excel\\"};
        Start.run(args);
    }
}