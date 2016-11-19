import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.json.JSONException;
import org.json.JSONObject;

import static spark.Spark.post;

/**
 * Created by Ryan on 19/11/2016.
 */
public class LoginWebSocketHandler {

    @OnWebSocketMessage
    public void getDetails(JSONObject details)
    {



        /* try {
            Login.getAttempt(details.getString("username"), details.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        } */
    }

}
