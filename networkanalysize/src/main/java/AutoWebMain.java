import base.Log;
import userService.networkBills.SetElement;

/**
 * Created by jlgaoyuan on 2018/11/4.
 *
 */
public class AutoWebMain {
    public static void main(String[] args) {
        if(args.length==1){
            if("debug".equals(args[0]) || "DEBUG".equals(args[0])){
                Log.setDebug(true);
            }
        }
        SetElement.run();
        //GetElement.run();
    }


}
