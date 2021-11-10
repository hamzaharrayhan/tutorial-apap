package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.StoreModel;

import java.util.List;

public interface StoreService  {
    void addStore(StoreModel store);
    void updateStore(StoreModel store);
    void deleteStore(Long idstore);
    List<StoreModel> getStoreList();
    StoreModel getStoreByIdStore(Long idStore);
    String generateStoreCode(StoreModel store);
    boolean checkErrorTime(StoreModel store);

}
