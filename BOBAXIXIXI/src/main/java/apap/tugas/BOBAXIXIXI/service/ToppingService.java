package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.ToppingModel;

import java.util.List;

public interface ToppingService  {
    void addTopping(ToppingModel Topping);
    void updateTopping(ToppingModel Topping);
    void deleteTopping(Long idTopping);
    List<ToppingModel> getToppingList();
    ToppingModel getToppingByIdTopping(Long idTopping);

}
