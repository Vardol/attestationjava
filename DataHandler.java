import java.util.LinkedList;

/**
 * Интерфейс отвечающий за работу с данными.
 * Ссделан в виде абстрактного интерфейса, чтобы была возможность делать множетсво реализаций для разных вариантов хранения данных.
 * Например, в файле, БД и т.д. 
 */
public abstract class DataHandler {

    protected String path;
    //делаю только геттер для пути к файлу, т.к. не хочу, чтобы что-то поломалось из-за смены пути файла в процессе работы
    // соответственно, логика работы в том, что на каждый файл д.б. отдельный экземпляр класса
    public String getPath() {
        return path;
    }

    //список игрушек ввсегда должен соответствовать списку в БД (файле), поэтому не д.б. метода прямого их изменения
    //соответственн, тоже делаю только геттер
    protected LinkedList<Toy> toys;
    public LinkedList<Toy> getToys() {
        return toys;
    }

    /**
     * Добавляет игрушку в хранилище
     * @param toy - игрушка, которую надо добавить
     * @return - возвращает True, если выполнено успешно
     */
    public abstract boolean addToy(Toy toy);

    /**Метод для записи внутренних данных в БД.
     * @return
     */
    protected abstract boolean writeData();
    
    //TODO: документацияю. Не стал пока тратить время на полное описание.
    
    public abstract boolean removeToy(Toy toy);
    public abstract boolean removeToyId(String id);
    public abstract boolean readData();
    public abstract LinkedList<Toy> fetchToyByName(String name);
    public abstract Toy fetchToyById(String id);
    public abstract boolean giveToy(Toy toy);
    public abstract boolean giveToyId(String id);
    public abstract boolean decreaseAmount(String id);
    public abstract boolean increaseAmount(String id);
    public abstract void clearAllData();
}