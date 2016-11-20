import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ImageWebSocketHandler
{

    private String sender, msg;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {

    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {

    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        if(message.equals(":computer:") || message.equals("images.png")) {
            System.out.println("in image");
            Chat.broadcastMessageI(sender = Chat.userUsernameMap.get(user), msg = message);
        }
        else{
            Chat.broadcastMessageI(sender = Chat.userUsernameMap.get(user), msg = message);
        }
    }


}
