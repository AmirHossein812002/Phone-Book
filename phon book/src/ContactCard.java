import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ContactCard extends Parent {
    private String name;
    private String number;
    private LinearGradient linearGradient;

    public ContactCard(String name, String number, LinearGradient linearGradient) {
        this.name = name;
        this.number = number;
        this.linearGradient = linearGradient;

        Rectangle bg = new Rectangle();
        bg.setFill(Color.valueOf("#ffffff"));
        bg.setHeight(60);
        bg.setWidth(200);
        bg.setArcHeight(60);
        bg.setArcWidth(50);

        Circle avatar = new Circle();
        avatar.setStyle(linearGradient.getCode());
        avatar.setRadius(20);
        avatar.setLayoutX(5);
        avatar.setLayoutY(30);

        Text firstLetter = new Text(name.substring(0, 1).toUpperCase());
        firstLetter.setFill(Color.valueOf("#ffffff"));
        firstLetter.setFont(Font.loadFont("file:src/UniSansThick.otf", 24));
        firstLetter.setLayoutX(-3.5);
        firstLetter.setLayoutY(38);

        Text nameTxt = new Text(name);
        nameTxt.setFill(Color.valueOf("#000000"));
        nameTxt.setFont(Font.loadFont("file:src/UniSansThick.otf", 16));
        nameTxt.setLayoutY(25);
        nameTxt.setLayoutX(30);

        Text numberTxt = new Text(number);
        numberTxt.setFill(Color.valueOf("#33312e"));
        numberTxt.setFont(Font.loadFont("file:src/UniSansThick.otf", 16));
        numberTxt.setLayoutX(30);
        numberTxt.setLayoutY(45);

        getChildren().addAll(bg, avatar, firstLetter, nameTxt, numberTxt);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PhoneBook.stage.setScene(Builder.contactCardBuilder(name , number, linearGradient));
            }
        });
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public LinearGradient getLinearGradient() {
        return linearGradient;
    }
}
