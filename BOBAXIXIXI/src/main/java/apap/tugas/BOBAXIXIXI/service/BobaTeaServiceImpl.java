package apap.tugas.BOBAXIXIXI.service;


import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.StoreBobaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.repository.BobaTeaDB;
import apap.tugas.BOBAXIXIXI.repository.StoreBobaDB;
import apap.tugas.BOBAXIXIXI.repository.StoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService {

    @Autowired
    BobaTeaDB bobaTeaDB;

    @Autowired
    StoreBobaDB storeBobaDB;

    @Autowired
    StoreDB storeDB;

    @Override
    public void addBobaTea(BobaTeaModel bobaTea){
        bobaTeaDB.save(bobaTea);
    }
    @Override
    public void updateBobaTea(BobaTeaModel bobaTea){
        bobaTeaDB.save(bobaTea);
    }

    @Override
    public void deleteBobaTea(Long idBobaTea){
        Optional<BobaTeaModel> bobaTea = bobaTeaDB.findByIdBobaTea(idBobaTea);
        if(bobaTea.isPresent()){
            bobaTeaDB.deleteById(idBobaTea);
        }
    }

    @Override
    public List<BobaTeaModel> getBobaTeaList(){
        return bobaTeaDB.findAll(Sort.by("idBobaTea").ascending());
    }

    @Override
    public BobaTeaModel getBobaTeaByIdBobaTea(Long idBobaTea){
        Optional<BobaTeaModel> bobaTea = bobaTeaDB.findByIdBobaTea(idBobaTea);
        if(bobaTea.isPresent()){
            return bobaTea.get();
        }
        return null;
    }

    @Override
    public Boolean checkErrorTime(BobaTeaModel boba) {
        Optional<BobaTeaModel> bobaTeaModel = bobaTeaDB.findByIdBobaTea(boba.getIdBobaTea());
        if (bobaTeaModel.isPresent()) {
            for (StoreBobaModel storeBobaModel : storeBobaDB.findAll()) {
                if (bobaTeaModel.get().getIdBobaTea().equals(storeBobaModel.getBoba().getIdBobaTea())) {
                    if (LocalTime.now().isAfter(storeBobaModel.getStore().getOpenHour()) && LocalTime.now().isBefore(storeBobaModel.getStore().getCloseHour())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void deleteStoreList(List<Long> store) {
        for (Long storeId : store){
            Optional<StoreModel> storeModel = storeDB.findByIdStore(storeId);
            if(storeModel.isPresent()){
                storeDB.deleteById(storeId);
            }
        }
    }


}
