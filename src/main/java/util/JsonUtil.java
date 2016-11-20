package util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Ryan on 20/11/2016.
 */
public class JsonUtil {

    public static String toJson(Object o)
    {
        return new Gson().toJson(o);
    }

    public static ResponseTransformer json()
    {
        return JsonUtil::toJson;
    }
}
