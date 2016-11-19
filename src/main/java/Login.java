/**
 * Created by Ryan on 19/11/2016.
 */
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.websocket.api.*;
import org.json.*;
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.*;
import static spark.Spark.*;

import org.apache.velocity.app.*;


public class Login {

    private static String Username = "test";
    private static String Password = "password";

    public static Boolean getAttempt(String username, String password) {

        // TODO setup database
        // Get the user details from the database

        // TEST FUNCTION
        if( ! username.equals(Username))
        {
            return false;
        }
        // Check the password

        if( ! password.equals(Password))
        {
            return false;
        }

        // return success or fail
        return true;

    }

    public static Boolean registerUser(String username, String password)
    {
        return false;
    }


    public static Route GetPage = (spark.Request request, spark.Response respone) ->
    {
        Map<String, Object> model = new HashMap<>();

        VelocityEngine conEngine = new VelocityEngine();
        conEngine.setProperty("runtime.references.string", true);
        conEngine.setProperty("resource.loader", "class");
        conEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityTemplateEngine vte = new VelocityTemplateEngine(conEngine);

        return vte.render(new ModelAndView(model, "/Velocity/login.vm"));
    };

    public static Route LoginPost = (spark.Request request, spark.Response response)-> {
        String u = request.queryParams("username");
        String p = request.queryParams("password");


        if (!u.equals(Username)) {
            return null;
        }

        if (!p.equals(Password)) {
            return null;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("auth-success", true);


        VelocityEngine conEngine = new VelocityEngine();
        conEngine.setProperty("runtime.references.strict", true);
        conEngine.setProperty("resource.loader", "class");
        conEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityTemplateEngine vte = new VelocityTemplateEngine(conEngine);

        return vte.render(new ModelAndView(model, "/Velocity/login.vm"));
    };
}
