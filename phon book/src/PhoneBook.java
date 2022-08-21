import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PhoneBook extends Application {
    public static ArrayList<Contact> contacts = new ArrayList<Contact>();
    public static File contactList = new File("contact's list.txt");
    public static Stage stage = new Stage();
    static {
        stage.setResizable(false);
        stage.setTitle("Phone Book");
        stage.getIcons().add(new Image("phoneIcon.png"));
        try {
            Scanner scanner = new Scanner(contactList);
            while (scanner.hasNext()) {
                String name = scanner.nextLine();
                String number = scanner.nextLine();
                contacts.add(new Contact(name, number));
            }
            contacts.sort(new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage.setScene(Builder.phoneBookBuilder());
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
