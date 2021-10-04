package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;

import java.util.List;

public interface BioskopService {

    void addBioskop(BioskopModel bioskop);
    void updateBioskop(BioskopModel bioskop);
    List<BioskopModel> getBioskopList();
    BioskopModel getBioskopByNoBioskop(Long idBioskop);
    List<BioskopModel> getBioskopByNamaBioskop();
    void deleteBioskop(BioskopModel bioskop);
    boolean waktuBenar(BioskopModel bioskop);
    boolean cekPenjaga(BioskopModel bioskop);
}
    List<BioskopModel> getBioskopList();
    BioskopModel getBioskopByIdBioskop(String idBioskop);

}
