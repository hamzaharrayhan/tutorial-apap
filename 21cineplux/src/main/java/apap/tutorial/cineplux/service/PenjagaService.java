package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;

public interface PenjagaService {
    void addPenjaga(PenjagaModel penjaga);

    PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga);

    void updatePenjaga(PenjagaModel penjaga);

    boolean waktuBenar(BioskopModel bioskop);

    void deletePenjaga(PenjagaModel penjaga);
}
