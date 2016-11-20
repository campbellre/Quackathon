/**
 * Created by Ryan on 19/11/2016.
 */
import HardCodedVals.HardCodedVals;
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.*;

import java.util.*;

import org.apache.velocity.app.*;


public class Login {

    private static String Username = "test";
    private static String Password = "password";

    public static VelocityTemplateEngine engine(){
        VelocityEngine conEngine = new VelocityEngine();
        conEngine.setProperty("runtime.references.string", true);
        conEngine.setProperty("resource.loader", "class");
        conEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityTemplateEngine vte = new VelocityTemplateEngine(conEngine);

        return vte;
    }

    public static Route GetPage = (spark.Request request, spark.Response response) ->
    {
        Map<String, Object> model = new HashMap<>();
        return engine().render(new ModelAndView(model, "/Velocity/login.vm"));
    };

    public static Route LoginPost = (spark.Request request, spark.Response response)-> {
        String u = request.queryParams("username");
        String p = request.queryParams("password");
        String path;

        HardCodedVals hcv = new HardCodedVals();
        Map<String, Object> model = new HashMap<>();
        if(hcv.checkUserValid(u, p))
        {
            model.put("auth-success", true);
            model.put("username", u);
            path = "/Velocity/home.vm";
        }
        else
        {
            model.put("auth-success", false);
            path = "/Velocity/login.vm";
        }


        return engine().render(new ModelAndView(model, path));
    };
}
