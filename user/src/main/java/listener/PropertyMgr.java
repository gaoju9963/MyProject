package listener;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by pengshu on 2016/11/11.
 */
public class PropertyMgr {
    private static Properties props = new Properties();

    static {
        try {
            PropertyMgr.class.getClassLoader();
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("listener.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
