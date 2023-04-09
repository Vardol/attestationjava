import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;

public class FileHandler extends DataHandler{
    /**
     * Конструктор создает экземпляр привязанным к конкретному файлу
     * Для работу с другой БД (файлом) необходимо создать новый экземпляр.
     * @param path - путь к текстовому файлу БД
     * @throws IOException
     */
    public FileHandler(String path) throws IOException {
        this.path = path;
        readData();
    }

    //Поскольку не планирую работать с большим количеством данных - упростил себе работу и просто записываю все данные при их изменении
    // в реальной программе надо было бы реализовывать логику именно добавления строки
    @Override
    public boolean addToy(Toy toy) {
        if (this.toys.contains(toy)) {
            return false;
        }
        return (this.toys.add(toy) && this.writeData());
    }

    @Override
    public boolean removeToy(Toy toy) {
        return toys.remove(toy) && this.writeData();
    }

    @Override
    public boolean removeToyId(String id) {
        Toy searched = null;
        for (Toy toy : toys) {
            if (toy.getId().equals(id)){
                searched = toy;
            }
        }

        if (searched != null) {
            return this.removeToy(searched) && this.writeData();
        } else {
            return false;
        }
    }

    @Override
    public LinkedList<Toy> fetchToyByName(String name) {
        LinkedList<Toy> searched = new LinkedList<>();
        for (Toy toy : toys) {
            if (toy.getName().equalsIgnoreCase(name)){
                searched.add(toy);
            }
        }
        return searched;
    }

    @Override
    public Toy fetchToyById(String id) {
        Toy searched = null;
        for (Toy toy : toys) {
            if (toy.getId().equals(id)){
                searched = toy;
            }
        }
        return searched;
    }

    // @Override
    // public Toy updateToy(String id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateToy'");
    // }


    protected boolean writeData() {
        try (FileWriter writer = new FileWriter("toys.txt",Charset.forName("UTF-8"))) {
            for (Toy toy : toys) {
                writer.write(toy.toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("IO exception");
            return false;
        }
    }

    @Override
    public boolean readData() {
        this.toys = new LinkedList<Toy>();
        try (FileReader reader = new FileReader(path, Charset.forName("UTF-8"))) {
            BufferedReader buffReader = new BufferedReader(reader);
            while (buffReader.ready()) {
                this.toys.add(Toy.parseToy(buffReader.readLine()));
            }
            return true;
        } catch (IOException e) {
            System.out.println("IO exception");
            return false;
        }
    }

    @Override
    public boolean giveToy(Toy toy) {
        return giveToyId(toy.getId());
    }

    @Override
    public boolean giveToyId(String id) {
        return this.decreaseAmount(id);
    }

    @Override
    public boolean decreaseAmount(String id) {
        this.fetchToyById(id).amount--;
        if (this.fetchToyById(id).amount <= 0){
            this.removeToyId(id);
        }
        return writeData();
    }

    @Override
    public boolean increaseAmount(String id) {
        this.fetchToyById(id).amount++;
        return writeData();
    }

    @Override
    public void clearAllData() {
        try (FileWriter writer = new FileWriter("toys.txt",Charset.forName("UTF-8"))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
