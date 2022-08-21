import java.util.Random;

public class Contact {
    private String name;
    private String number;
    private ContactCard contactCard;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        Random random = new Random();
        this.contactCard = new ContactCard(name, number, Builder.linearGradients.get(random.nextInt(7)));
    }

    public ContactCard getContactCard() {
        return contactCard;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
