import java.util.LinkedList;

//Примерно накидал возможный интерфейс для работы с пользователем.
//Реализовывать не стал, т.к. в задании UI не требуется.

public interface UI {
    public void showData(Toy toy);
    public void showData(LinkedList<Toy> toys);
    public void showData(String message);
    public Toy readToy(Toy toy);
    public String readData();
    public String readData(String promptingMessage);
    public void giveToy(Toy toy);
    public Toy takeToy(Toy toy);
}
