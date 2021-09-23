package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import apap.tutorial.cineplux.repository.PenjagaDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PenjagaServiceImpl implements PenjagaService{
    @Autowired
    PenjagaDB penjagaDB;
    BioskopDB bioskopDB;

    @Override
    public void addPenjaga(PenjagaModel penjaga) {penjagaDB.save(penjaga);}

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        Optional<PenjagaModel> penjaga = penjagaDB.findByNoPenjaga(noPenjaga);
        if(penjaga.isPresent()){
            return penjaga.get();
        }
        return null;
    }

    @Override
    public void updatePenjaga(PenjagaModel penjaga) { penjagaDB.save(penjaga); }

    @Override
    public boolean waktuBenar(BioskopModel bioskop) {
        LocalTime localTime = LocalTime.now();
        if (bioskop.getWaktuTutup().isBefore(localTime)){
            return true;
        } else if (bioskop.getWaktuBuka().isAfter(localTime)){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void deletePenjaga(PenjagaModel penjaga) {
        penjagaDB.delete(penjaga);
    }
}
