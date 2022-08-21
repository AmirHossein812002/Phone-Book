
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Builder {
    public static LinearGradient blue = new LinearGradient("-fx-fill: linear-gradient(#2193b0, #6dd5ed);");
    public static LinearGradient gray = new LinearGradient("-fx-fill: linear-gradient(#0F2027, #203A43, #2C5364);");
    public static LinearGradient orange = new LinearGradient("-fx-fill: linear-gradient(#f12711, #f5af19);");
    public static LinearGradient purple = new LinearGradient("-fx-fill: linear-gradient(#654ea3, #eaafc8);");
    public static LinearGradient green = new LinearGradient("-fx-fill: linear-gradient(#a8ff78, #78ffd6);");
    public static LinearGradient red = new LinearGradient("-fx-fill: linear-gradient(#FF512F, #DD2476);");
    public static LinearGradient black = new LinearGradient("-fx-fill: linear-gradient(#000000, #434343);");
    public static ArrayList<LinearGradient> linearGradients = new ArrayList<LinearGradient>();

    static {
        linearGradients.add(blue);
        linearGradients.add(gray);
        linearGradients.add(orange);
        linearGradients.add(purple);
        linearGradients.add(green);
        linearGradients.add(red);
        linearGradients.add(black);
    }

    public static Scene phoneBookBuilder() {
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #d1cec5;");
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        TextField searchBar = new TextField();
        searchBar.setPromptText("search");
        PhoneBook.contacts.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        searchBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Button addButton = new Button("ADD");
                addButton.setPrefWidth(215);
                addButton.setTextFill(Color.valueOf("#ffffff"));
                addButton.setStyle("-fx-background-color: #1DA1F2;");
                addButton.setFont(Font.loadFont("file:src/UniSansThick.otf", 24));
                addButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        PhoneBook.stage.setScene(Builder.addBuilder());
                    }
                });

                searchBar.setPrefWidth(215);
                ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
                for (int i = 0; i < PhoneBook.contacts.size(); i++) {
                    if (PhoneBook.contacts.get(i).getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                        contactArrayList.add(PhoneBook.contacts.get(i));
                    }
                }
                vBox.getChildren().remove(1, vBox.getChildren().size());
                vBox.getChildren().add(addButton);
                for (int i = 0; i < contactArrayList.size(); i++) {
                    vBox.getChildren().add(contactArrayList.get(i).getContactCard());
                    if (i != contactArrayList.size() - 1) {
                        Separator separator = new Separator(Orientation.HORIZONTAL);
                        vBox.getChildren().add(separator);
                    }

                }
            }
        });
        searchBar.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Button addButton = new Button("ADD");
                addButton.setPrefWidth(215);
                addButton.setTextFill(Color.valueOf("#ffffff"));
                addButton.setStyle("-fx-background-color: #1DA1F2;");
                addButton.setFont(Font.loadFont("file:src/UniSansThick.otf", 24));
                addButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        PhoneBook.stage.setScene(Builder.addBuilder());
                    }
                });

                ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
                for (int i = 0; i < PhoneBook.contacts.size(); i++) {
                    if (PhoneBook.contacts.get(i).getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                        contactArrayList.add(PhoneBook.contacts.get(i));
                    }
                }
                vBox.getChildren().remove(1, vBox.getChildren().size());
                vBox.getChildren().add(addButton);
                for (int i = 0; i < contactArrayList.size(); i++) {
                    vBox.getChildren().add(contactArrayList.get(i).getContactCard());
                    if (i != contactArrayList.size() - 1) {
                        Separator separator = new Separator(Orientation.HORIZONTAL);
                        vBox.getChildren().add(separator);
                    }

                }
            }
        });
        vBox.getChildren().add(searchBar);

        Button addButton = new Button("ADD");
        addButton.setPrefWidth(215);
        addButton.setTextFill(Color.valueOf("#ffffff"));
        addButton.setStyle("-fx-background-color: #1DA1F2;");
        addButton.setFont(Font.loadFont("file:src/UniSansThick.otf", 24));
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PhoneBook.stage.setScene(Builder.addBuilder());
            }
        });

        vBox.getChildren().add(addButton);

        for (int i = 0; i < PhoneBook.contacts.size(); i++) {
            vBox.getChildren().add(PhoneBook.contacts.get(i).getContactCard());
            if (i != PhoneBook.contacts.size() - 1) {
                Separator separator = new Separator(Orientation.HORIZONTAL);
                vBox.getChildren().add(separator);
            }

        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setMaxHeight(450);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        return new Scene(scrollPane);
    }

    public static Scene contactCardBuilder(String name, String number, LinearGradient linearGradient) {
        Group root = new Group();

        Rectangle bg = new Rectangle();
        bg.setFill(Color.valueOf("#d1cec5"));
        bg.setWidth(250);
        bg.setHeight(400);

        Rectangle bg2 = new Rectangle();
        bg2.setFill(Color.valueOf("#FFFFFF"));
        bg2.setWidth(200);
        bg2.setHeight(300);
        bg2.setArcWidth(200);
        bg2.setArcHeight(150);
        bg2.setLayoutX(25);
        bg2.setLayoutY(70);

        Circle avatar = new Circle();
        avatar.setStyle(linearGradient.getCode());
        avatar.setRadius(50);
        avatar.setLayoutX(125);
        avatar.setLayoutY(80);

        Text firstLetter = new Text(name.substring(0, 1).toUpperCase());
        firstLetter.setFont(Font.loadFont("file:src/UniSansThick.otf", 90));
        firstLetter.setLayoutY(110);
        firstLetter.setLayoutX(95);
        firstLetter.setFill(Color.valueOf("#ffffff"));

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("name");
        nameTextField.setText(name);
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 2) {
                firstLetter.setText(newValue.substring(0, 1).toUpperCase());
            }
        });
        nameTextField.setLayoutX(55);
        nameTextField.setLayoutY(150);

        TextField numberTextField = new TextField();
        numberTextField.setPromptText("number");
        numberTextField.setText(number);
        numberTextField.setLayoutX(55);
        numberTextField.setLayoutY(200);

        Button saveButton = new Button("Save");
        saveButton.setTextFill(Color.valueOf("#ffffff"));
        saveButton.setStyle("-fx-background-color: #1DA1F2;");
        saveButton.setFont(Font.loadFont("file:src/UniSansThick.otf", 24));
        saveButton.setLayoutY(250);
        saveButton.setLayoutX(80);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < PhoneBook.contacts.size(); i++) {
                    if (PhoneBook.contacts.get(i).getName().equals(name) && PhoneBook.contacts.get(i).getNumber().equals(number)) {
                        PhoneBook.contacts.remove(i);
                        PhoneBook.contacts.add(i, new Contact(nameTextField.getText(), numberTextField.getText()));
                        PhoneBook.stage.setScene(Builder.phoneBookBuilder());
                        try {
                            FileWriter fileWriter = new FileWriter(PhoneBook.contactList);
                            PrintWriter printWriter = new PrintWriter(PhoneBook.contactList);
                            printWriter.write("");
                            PhoneBook.contacts.sort(new Comparator<Contact>() {
                                @Override
                                public int compare(Contact o1, Contact o2) {
                                    return o1.getName().compareTo(o2.getName());
                                }
                            });
                            for (int j = 0; j < PhoneBook.contacts.size(); j++) {
                                fileWriter.append(PhoneBook.contacts.get(j).getName() + "\n" + PhoneBook.contacts.get(j).getNumber() + "\n");
                            }
                            printWriter.close();
                            fileWriter.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });

        root.getChildren().addAll(bg, bg2, avatar, firstLetter, nameTextField, numberTextField, saveButton);
        return new Scene(root);
    }

    public static Scene addBuilder() {
        Group root = new Group();

        Rectangle bg = new Rectangle();
        bg.setFill(Color.valueOf("#d1cec5"));
        bg.setWidth(250);
        bg.setHeight(400);

        Rectangle bg2 = new Rectangle();
        bg2.setFill(Color.valueOf("#FFFFFF"));
        bg2.setWidth(200);
        bg2.setHeight(300);
        bg2.setArcWidth(200);
        bg2.setArcHeight(150);
        bg2.setLayoutX(25);
        bg2.setLayoutY(70);

        Circle avatar = new Circle();
        Random random = new Random();
        avatar.setStyle(linearGradients.get(random.nextInt(7)).getCode());
        avatar.setRadius(50);
        avatar.setLayoutX(125);
        avatar.setLayoutY(80);

        Text firstLetter = new Text("");
        firstLetter.setFont(Font.loadFont("file:src/UniSansThick.otf", 90));
        firstLetter.setLayoutY(110);
        firstLetter.setLayoutX(95);
        firstLetter.setFill(Color.valueOf("#ffffff"));

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("name");
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 2) {
                firstLetter.setText(newValue.substring(0, 1).toUpperCase());
            }
        });
        nameTextField.setLayoutX(55);
        nameTextField.setLayoutY(150);

        TextField numberTextField = new TextField();
        numberTextField.setPromptText("number");
        numberTextField.setLayoutX(55);
        numberTextField.setLayoutY(200);

        Button saveButton = new Button("Save");
        saveButton.setTextFill(Color.valueOf("#ffffff"));
        saveButton.setStyle("-fx-background-color: #1DA1F2;");
        saveButton.setFont(Font.loadFont("file:src/UniSansThick.otf", 24));
        saveButton.setLayoutY(250);
        saveButton.setLayoutX(80);
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PhoneBook.contacts.add(new Contact(nameTextField.getText(), numberTextField.getText()));
                PhoneBook.stage.setScene(Builder.phoneBookBuilder());
                try {
                    FileWriter fileWriter = new FileWriter(PhoneBook.contactList);
                    PrintWriter printWriter = new PrintWriter(PhoneBook.contactList);
                    printWriter.write("");
                    PhoneBook.contacts.sort(new Comparator<Contact>() {
                        @Override
                        public int compare(Contact o1, Contact o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    for (int j = 0; j < PhoneBook.contacts.size(); j++) {
                        fileWriter.append(PhoneBook.contacts.get(j).getName() + "\n" + PhoneBook.contacts.get(j).getNumber() + "\n");
                    }
                    printWriter.close();
                    fileWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        root.getChildren().addAll(bg, bg2, avatar, firstLetter, nameTextField, numberTextField, saveButton);
        return new Scene(root);
    }
}
