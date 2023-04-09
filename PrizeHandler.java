import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * PrizeGenerator
 */
public class PrizeHandler {

    public static LinkedList<String> generatePrizeList(DataHandler dataHandler, int amountOfPrizes){
        HashMap<String,Integer> prizeList = new HashMap<>();
        LinkedList<String> result = new LinkedList<>();
        Random rand = new Random();
        while (amountOfPrizes > 0) {
            boolean validitycheck = false; //маркер валидности - наличия в списке игрушек с ненулевой вероятностью попадания в выборку
            for (Toy toy : dataHandler.getToys()) {
                if (amountOfPrizes > 0) {
                    if (toy.getChance() > 0) {
                        validitycheck = true;
                        if (toy.getChance() < rand.nextDouble()){
                            if (prizeList.containsKey(toy.getId())) {
                                if (toy.getAmount() > prizeList.get(toy.getId())) {
                                    prizeList.put(toy.getId(), 1 + prizeList.get(toy.getId()));
                                    result.add(toy.getId());
                                    amountOfPrizes--;
                                }
                            } else {
                                if (toy.getAmount() > 0) {
                                    prizeList.put(toy.getId(), 1);
                                    result.add(toy.getId());
                                    amountOfPrizes--;
                                }
                            }
                        }
                    }
                }
            }
            if (!validitycheck) {return null;} //если пробежав по списку мы не нашли валидных игрушек - возвращаем null     
        }
        return result;

    }
    
    public static boolean setChances(DataHandler dataHandler){
        if (dataHandler.getToys().size() > 0){
            double chance = 1 / (double)dataHandler.getToys().size();
            for (Toy toy : dataHandler.getToys()) {
                toy.setChance(chance);
            }
            return dataHandler.writeData();
        } else {
            return false;
        }
    }

}