package apap.tugas.BOBAXIXIXI.service;


import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.StoreBobaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.repository.ManagerDB;
import apap.tugas.BOBAXIXIXI.repository.StoreBobaDB;
import apap.tugas.BOBAXIXIXI.repository.StoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class StoreBobaServiceImpl implements StoreBobaService {

    @Autowired
    StoreBobaDB storeBobaDB;


    @Override
    public String productionCode(StoreBobaModel storeBoba) {
        String storeId = storeBoba.getStore().getIdStore().toString();
        String topping = storeBoba.getBoba().getTopping().getIdTopping() == 11 ? "0" : "1";
        String bobaId = storeBoba.getBoba().getIdBobaTea().toString();
        String nstr = "PC";
        if (storeId.length() == 1) {
            nstr = nstr + "00" + storeId;
        } if (storeId.length() == 2) {
            nstr = nstr + "0" + storeId;
        } if (storeId.length() == 3) {
            nstr = nstr + storeId;
        }
        nstr = nstr + topping;
        if (bobaId.length() == 1) {
            nstr = nstr + "00" + bobaId;
        } if (bobaId.length() == 2) {
            nstr = nstr + "0" + bobaId;
        } if (bobaId.length() == 3) {
            nstr = nstr + bobaId;
        }

        return nstr;
    }

    @Override
    public void addStoreBoba(StoreBobaModel storeBoba) {
        storeBoba.setProduction_code(productionCode(storeBoba));
        storeBobaDB.save(storeBoba);
    }

    @Override
    public StoreBobaModel getStoreBobaByIdStoreBoba(Long idStoreBoba) {
        Optional<StoreBobaModel> storeBoba = storeBobaDB.findByIdStoreBoba(idStoreBoba);
        if(storeBoba.isPresent()){
            return storeBoba.get();
        }
        return null;
    }
}
