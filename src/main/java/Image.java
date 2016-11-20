import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 20/11/2016.
 */
public class Image {

    public static List<String> GetImageList()
    {
        System.out.println("in get image");
        List<String> fileList = new ArrayList<String>();
        URL resource = Image.class.getResource("public/images");
        File imageDir = null;
        try {
            imageDir = Paths.get(resource.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            for (File f : imageDir.listFiles()) {
                if (f.isDirectory()) {
                } else if (f.isFile()) {
                    System.out.println(f.getName());
                    fileList.add(f.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    };
}
