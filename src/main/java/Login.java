/**
 * Created by Ryan on 19/11/2016.
 */
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.*;

import java.util.*;

import HardCodedVals.HardCodedVals;

import org.apache.velocity.app.*;


public class Login {

    private static String Username = "test";
    private static String Password = "password";

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

        System.out.println("hey");

        HardCodedVals hcv = new HardCodedVals();

        Map<String, Object> model = new HashMap<>();
        Map<String, Object> loggedInModel = new HashMap<>();

        String page;

        if(hcv.checkUserValid(u, p))
        {
            model.put("auth-success", true);
            model.put("username", u);
            model.put("posts", hcv.getUserPosts(u));
            page = "/Velocity/Feed.vm";

        }
        else
        {
            model.put("auth-success", false);
            page = "/Velocity/login.vm";
        }

        VelocityEngine conEngine = new VelocityEngine();
        conEngine.setProperty("runtime.references.strict", true);
        conEngine.setProperty("resource.loader", "class");
        conEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityTemplateEngine vte = new VelocityTemplateEngine(conEngine);

        return vte.render(new ModelAndView(model, page));
    };
}
