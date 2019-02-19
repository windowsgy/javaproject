package userService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jlgaoyuan on 2018/11/6.
 *
 */
public class Stru_Bill {
    private String id ;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Map<String, String> getBillEditMap() {
        return billEditMap;
    }
    public void setBillEditMap(Map<String, String> billEditMap) {
        this.billEditMap = billEditMap;
    }
    public Map<String, String> getStepEditMap() {
        return stepEditMap;
    }
    public void setStepEditMap(Map<String, String> stepEditMap) {
        this.stepEditMap = stepEditMap;
    }
    public Map<String, String> getStepDelMap() {
        return stepDelMap;
    }
    public void setStepDelMap(Map<String, String> stepDelMap) {
        this.stepDelMap = stepDelMap;
    }
    private Map<String,String> billEditMap = new HashMap<>();
    private Map<String,String> stepEditMap = new HashMap<>();
    private Map<String,String> stepDelMap = new HashMap<>();
}
