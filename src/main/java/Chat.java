import org.eclipse.jetty.websocket.api.*;
import org.json.*;
import spark.Route;

import java.io.IOException;
import java.text.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
import static spark.Spark.*;
import static util.JsonUtil.json;

public class Chat {

    // this map is shared between sessions and threads, so it needs to be thread-safe (http://stackoverflow.com/a/2688817)
    static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    static int nextUserNumber = 1; //Assign to username for next connecting user

    public static void main(String[] args) {
        staticFiles.location("/public"); //index.html is served at localhost:4567 (default port)
        staticFiles.expireTime(600);
        webSocket("/chat", ChatWebSocketHandler.class);
        webSocket("/images", ImageWebSocketHandler.class);
        init();

        get("/login", Login.GetPage);
        post("/login", Login.LoginPost);

        get("/home", Home.GetPage);


        //redirect.get("/", "/login");
        /*
        get("/images",  (req, res) -> Image.GetImageList(), json());

        after((req, res) -> {
            res.type("application/json");
        });
        */
    }

    //Sends a message from one user to all users, along with a list of current usernames
    public static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSender(sender, message))
                        .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void broadcastMessageI(String sender, String message) {
        System.out.println("in broadcast");
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlMessageFromSenderI(sender, message))
                        .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void sendImages(String sender)
    {
        System.out.println("in send image");
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                    .put("imagePath", createImageHtml(sender))
            ));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private static String createImageHtml(String sender) {
        System.out.println("in html image");
        return article().with(
                img().withSrc("images.png")
        ).render();
    }

    //Builds a HTML element with a sender-name, a message, and a timestamp,
    private static String createHtmlMessageFromSender(String sender, String message) {
        System.out.println("in default html");
        return article().with(
                b(sender + " says:"),
                p(message),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

    //Builds a HTML element with a sender-name, a message, and a timestamp,
    private static String createHtmlMessageFromSenderI(String sender, String message) {
        System.out.println("in html");
        return article().with(
                b(sender + " says:"),
                img().withSrc("/images/" + message),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

    private static void SendImage(String sender, String path)
    {
        System.out.println("in broadcast");
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()
                        .put("userMessage", createHtmlImageMessageFromSender(sender, path))
                        .put("userlist", userUsernameMap.values())
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static String createHtmlImageMessageFromSender(String sender, String path) {
        System.out.println("in html");
        return article().with(
                b(sender + " says:"),
                img().withSrc(path),
                span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }
}
