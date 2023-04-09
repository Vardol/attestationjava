/**
 * Интерфейс отвечающий за работу с данными.
 * Ссделан в виде абстрактного интерфейса, чтобы была возможность делать множетсво реализаций для разных вариантов хранения данных.
 * Например, в файле, БД и т.д. 
 */
public interface DBHandler {

    /**
     * Добавляет несколько игрушек в хранилище
     * @param toy - массив игрушек для добавления в хранилище
     * @return - возвращает True, если выполнено успешно
     */
    public boolean addToy(Toy[] toy);

    /**
     * Добавляет игрушку в хранилище
     * @param toy - игрушка, которую надо добавить
     * @return - возвращает True, если выполнено успешно
     */
    public boolean addToy(Toy toy);

    //TODO: документацияю. Не стал пока тратить время на полное описание.
    public boolean removeToy(Toy toy, int amount);
    public boolean removeToy(int id);
    public boolean removeToy(String name);
    public Toy fetchToy(String name);
    public Toy fetchToy(int id);
    public Toy fetchToy(String name);

    
}