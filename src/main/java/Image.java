import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 20/11/2016.
 */
public class Image {

    public static ArrayList<MyFile> GetImageList()
    {
        System.out.println("in get image");
        Map<String, MyFile> fMap = new HashMap<String, MyFile>();
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
                    MyFile file = new MyFile();
                    file.setName(f.getName());
                    file.setPath(f.getPath());
                    fMap.put(file.getName(), file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(fMap.values());
    };
}
