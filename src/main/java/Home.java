import org.apache.velocity.app.VelocityEngine;
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Connor on 20/11/2016.
 */
public class Home
{
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
        return engine().render(new ModelAndView(model, "/Velocity/home.vm"));
    };
}
