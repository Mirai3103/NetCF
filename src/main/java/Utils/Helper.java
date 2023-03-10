package Utils;

import Migrate.Main;

import java.io.File;
import java.net.URL;

public class Helper {
    public static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }
    public static File getResourceFile (String path) {
        var  a=  Helper.class.getResource(path).getPath();
       return   new File(a);

    }
}
