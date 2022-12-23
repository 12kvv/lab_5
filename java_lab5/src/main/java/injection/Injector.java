package injection;

import annotations.AutoInjectable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    private File data;

    public Injector(String filePath) {
        this.data = new File(filePath);
    }

    public <T> T inject (T obj) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(new FileReader(data));

        Class objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for (Field f: fields) {
            Annotation a = f.getAnnotation(AutoInjectable.class);
            f.setAccessible(true);
            if (a != null) {
                String typeName = properties.getProperty(f.getType().getName());
                Object classObject = Class.forName(typeName).newInstance();
                f.set(obj, classObject);
            }
        }

        return obj;
    }

}