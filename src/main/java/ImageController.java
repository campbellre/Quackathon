import static spark.Spark.after;
import static spark.Spark.get;
import static util.JsonUtil.json;

/**
 * Created by Ryan on 20/11/2016.
 */
public class ImageController {

    public ImageController()
    {
        get("/images",  (req, res) -> Image.GetImageList(), json());

        after((req, res) -> {
            res.type("application/json");
        });
    }



}
