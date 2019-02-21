package etl.structTransfer;

import base.TranslationUtils;
import etl.beans.AccessOrderBeans;

import java.util.ArrayList;
import java.util.List;

public class StruTransfer {

    public static List<String> beanToJson(List<AccessOrderBeans> list){
        TranslationUtils translationUtils = new TranslationUtils();
        List<String> listJson = new ArrayList<>();
        for (AccessOrderBeans beans : list) {
            listJson.add(translationUtils.beanToJson(beans));
        }
        return listJson;
    }
}