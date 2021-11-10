package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.StoreBobaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;

import java.util.List;

public interface StoreBobaService  {
    public String productionCode(StoreBobaModel storeBoba);
    public void addStoreBoba(StoreBobaModel storeBoba);
    StoreBobaModel getStoreBobaByIdStoreBoba(Long idStoreBoba);

}
