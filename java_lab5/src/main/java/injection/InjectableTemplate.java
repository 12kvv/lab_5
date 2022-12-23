package injection;

import annotations.AutoInjectable;
import interfaces.FirstInterface;
import interfaces.SecondInterface;
import java.io.IOException;

public class InjectableTemplate {
    @AutoInjectable
    private FirstInterface field1;
    @AutoInjectable
    private SecondInterface field2;

    public void printActions() {
        field1.doAction();
        field2.doAction();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String[] filePaths = {
                "C://Users/User/IdeaProjects/java_lab5/src/main/resources/test1.properties",
                "C://Users/User/IdeaProjects/java_lab5/src/main/resources/test2.properties",
                "C://Users/User/IdeaProjects/java_lab5/src/main/resources/test3.properties",
                "C://Users/User/IdeaProjects/java_lab5/src/main/resources/test4.properties"
        };

        for (int i = 0; i < filePaths.length; i++) {
            System.out.println("Test N" + (i + 1) + ':');
            InjectableTemplate test = (new Injector(filePaths[i])).inject(new InjectableTemplate());
            test.printActions();
            System.out.println("_________________________________");
        }
    }
}