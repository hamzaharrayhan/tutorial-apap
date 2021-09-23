package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.repository.BioskopDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class BioskopServiceImpl implements BioskopService {
    @Autowired
    BioskopDB bioskopDB;

    @Override
    public void addBioskop(BioskopModel bioskop){
        bioskopDB.save(bioskop);
    }

    @Override
    public void updateBioskop(BioskopModel bioskop){
        bioskopDB.save(bioskop);
    }

    @Override
    public List<BioskopModel> getBioskopList(){
        return bioskopDB.findAll();
    }

    @Override
    public BioskopModel getBioskopByNoBioskop(Long noBioskop){
        Optional<BioskopModel> bioskop = bioskopDB.findByNoBioskop(noBioskop);
        if(bioskop.isPresent()){
            return bioskop.get();
        }
        return null;
    }

    @Override
    public void deleteBioskop(BioskopModel bioskop) {
        bioskopDB.delete(bioskop);
    }

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
    public boolean cekPenjaga(BioskopModel bioskop) {
        if (bioskop.getListPenjaga().isEmpty() == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BioskopModel> getBioskopByNamaBioskop(){
        return bioskopDB.findAll(Sort.by(Sort.Direction.ASC, "namaBioskop"));
    }
}
