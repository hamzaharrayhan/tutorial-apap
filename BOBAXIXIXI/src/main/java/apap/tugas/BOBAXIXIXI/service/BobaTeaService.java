package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;

import java.util.List;

public interface BobaTeaService  {
    void addBobaTea(BobaTeaModel bobaTea);
    void updateBobaTea(BobaTeaModel bobaTea);
    void deleteBobaTea(Long idBobaTea);
    List<BobaTeaModel> getBobaTeaList();
    BobaTeaModel getBobaTeaByIdBobaTea(Long idBobaTea);

    Boolean checkErrorTime(BobaTeaModel boba);
    void deleteStoreList(List<Long> store);
}
