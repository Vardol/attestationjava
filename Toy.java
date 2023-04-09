import java.util.LinkedList;
import java.util.UUID;
import java.lang.Math;

public class Toy {
    protected String id;

    public String getId() {
        return this.id;
    }

    protected String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String description; //описание товара
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected int amount; //количество товара
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    protected int price; //цена товара
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    protected double chance; //вероятность выпадения в розыгрыше
    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    protected LinkedList<String> tags; //список тэгов для товара, для поиска по критериям
    public LinkedList<String> getTags() {
        return tags;
    }

    // полный конструктор - без вероятности, т.к. за нее отвечает хэндлер розыгрыша
    public Toy(String name, int amount, int price, String description, LinkedList<String> tags) {
        this.id = UUID.randomUUID().toString();
        if (name == null || name.isBlank()) {
            System.out.println("Empty name. Using default name.");
            this.name = "default";
        }
        else {this.name = name;}

        if (amount < 0) {
            System.out.println("Negative amount. Setting amount to 0.");
            this.amount = 0;
        }
        else {this.amount = amount;}

        if (price < 0) {
            System.out.println("Negative price. Setting price to 0.");
            this.price = 0;
        }
        else {this.price = price;}

        if (description == null) {this.description = "";}
        else {this.description = description;}

        if (tags == null) {this.tags = new LinkedList<String>();}
        else {this.tags = new LinkedList<String>(tags);}
    }

    // конструктор без описания и без тэгов
    public Toy(String name, int amount, int price) {
        this(name, amount, price, "", null);
    }

    // конструктор без описания
    public Toy(String name, int amount, int price, LinkedList<String> tags) {
        this(name, amount, price, "", tags);
    }

    // конструктор без тэгов
    public Toy(String name, int amount, int price, String description) {
        this(name, amount, price, description, null);
    }

    // конструктор без описания и без цены и без тэгов
    public Toy(String name, int amount) throws Exception {
        this(name, amount, 1, "", null);
    }

    // конструктор без цены и без тэгов
    public Toy(String name, int amount, String description) {
        this(name, amount, 1, description, null);
    }

    //TODO: подумать надо ли конструкторы в других вариантах



    public boolean addTag(String tag){
        return this.tags.add(tag);
    }

    public boolean removeTag(String tag){
        return this.tags.remove(tag);
    }

    @Override
    public String toString(){
        String result = "";
        return result.concat("<").concat(this.id).concat("> ").concat(this.name).concat(" остаток: ")
        .concat(Integer.toString(this.amount)).concat("шт., цена:").concat(Integer.toString(this.price)).
        concat("руб., вероятность выпадения: ").concat(Double.toString((Math.floor(chance*100)))).
        concat("%, тэги игрушки: ").concat(this.tags.toString()).concat("\nОписание: ").concat(this.description);
    }

}
