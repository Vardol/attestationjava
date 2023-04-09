/**
 * Интерфейс отвечающий за работу с данными.
 * Ссделан в виде абстрактного интерфейса, чтобы была возможность делать множетсво реализаций для разных вариантов хранения данных.
 * Например, в файле, БД и т.д. 
 */
public interface DataHandler {

    /**
     * Добавляет игрушку в хранилище
     * @param toy - игрушка, которую надо добавить
     * @return - возвращает True, если выполнено успешно
     */
    public boolean addToy(Toy toy);

    //TODO: документацияю. Не стал пока тратить время на полное описание.
    public boolean removeToy(Toy toy, int amount);
    public boolean removeSingleToy(Toy toy);
    public boolean removeSingleToy(String id);
    public boolean removeToy(String id, int amount);
    public Toy fetchToyByName(String name);
    public Toy fetchToyById(String id);
    public Toy updateToy(String id);
    
}