package base;

/**
 * Created by jlgaoyuan on 2018/11/12.
 *
 */
public class Reflect {

    public static <T> T createInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

}
