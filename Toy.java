import java.util.UUID;

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

    // полный конструктор - без вероятности, т.к. за нее отвечает хэндлер розыгрыша
    public Toy(String name, int amount, int price, String description) {
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

        this.chance = 0;

        if (description == null) {this.description = "";}
        else {this.description = description;}
    }


    //Приватный конструктор для формирования Toy со считанным извне id и chance и без проверок - для парсера.
    private Toy(String id, String name, int amount, int price, double chance, String description) {
        this.id = id;

        this.name = name;

        this.amount = amount;

        this.price = price;

        this.chance = 0;

        this.description = description;
    }

    // конструктор без описания
    public Toy(String name, int amount, int price) {
        this(name, amount, price, "нет описания");
    }

    // конструктор без описания и без цены
    public Toy(String name, int amount) throws Exception {
        this(name, amount, 0, "нет описания");
    }

    // конструктор без цены
    public Toy(String name, int amount, String description) {
        this(name, amount, 0, description);
    }

    public static Toy parseToy(String input){
        String[] inputArray = input.split(";");
        Toy result = new Toy(inputArray[0],inputArray[1],Integer.parseInt(inputArray[2]),
        Integer.parseInt(inputArray[3]),Double.parseDouble(inputArray[4]),inputArray[5]);
        return result;
    }

    @Override
    public String toString(){
        String result = "";
        return result.concat(this.id).concat(";").concat(this.name).concat(";")
        .concat(Integer.toString(this.amount)).concat(";").concat(Integer.toString(this.price))
        .concat(";").concat(Double.toString(chance)).concat(";").concat(this.description).concat(";\n");
    }

    @Override
    public boolean equals(Object o) {

        // сравниваем с собой 
        if (o == this) {
            return true;
        }
 
        // проверяем является ли о - объектом класса Toy
        if (!(o instanceof Toy)) {
            return false;
        }

        Toy t = (Toy)o;

        // сравниваем по ID
        return t.getId().equals(this.id);
    }
}
