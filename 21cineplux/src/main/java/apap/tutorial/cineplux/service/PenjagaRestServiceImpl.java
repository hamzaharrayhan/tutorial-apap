package apap.tutorial.cineplux.service;


import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import apap.tutorial.cineplux.repository.PenjagaDB;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Optional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PenjagaRestServiceImpl implements PenjagaRestService {
    private final WebClient webClient;

    @Autowired
    PenjagaDB penjagaDB;

    @Override
    public PenjagaModel createPenjaga(PenjagaModel penjaga) {
        return penjagaDB.save(penjaga);
    }

    @Override
    public List<PenjagaModel> retrieveListPenjaga() {
        return penjagaDB.findAll();
    }

    @Override
    public PenjagaModel getPenjagaByNoPenjaga(Long noPenjaga) {
        Optional<PenjagaModel> penjaga = penjagaDB.findByNoPenjaga(noPenjaga);
        if (penjaga.isPresent()) {
            return penjaga.get();
        }
        throw new NoSuchElementException();
    }

    @Override
    public PenjagaModel updatePenjaga(Long noPenjaga, PenjagaModel penjagaUpdate) {
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        BioskopModel bioskop = penjaga.getBioskop();
        if ((now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup()))) {
            penjaga.setNamaPenjaga(penjagaUpdate.getNamaPenjaga());
            penjaga.setJenisKelamin(penjagaUpdate.getJenisKelamin());
            penjaga.setBioskop(penjagaUpdate.getBioskop());
            return penjagaDB.save(penjaga);
        }
        throw new UnsupportedOperationException("Bioskop still Open!");
    }

    @Override
    public void deletePenjaga(Long noPenjaga) {
        LocalTime now = LocalTime.now();
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        BioskopModel bioskop = penjaga.getBioskop();

        if ((now.isBefore(bioskop.getWaktuBuka()) || now.isAfter(bioskop.getWaktuTutup()))) {
            penjagaDB.delete(penjaga);
        } else {
            throw new UnsupportedOperationException("Bioskop still Open!");
        }
    }

    public PenjagaRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.umurUrl).build();
    }

    @Override
    public String getUmur(Long noPenjaga) {
        PenjagaModel penjaga = getPenjagaByNoPenjaga(noPenjaga);
        String namaPenjaga = penjaga.getNamaPenjaga();
        if (penjaga.getNamaPenjaga().length() > 1) {
            String[] namaDepan = penjaga.getNamaPenjaga().split(" ");
            String nama = this.webClient.get().uri("/?name="+namaDepan[0]).retrieve().bodyToMono(String.class).block();
            System.out.println(nama);
            return this.webClient.get().uri("/?name="+namaDepan[0]).retrieve().bodyToMono(String.class).block();
        }

        return this.webClient.get().uri("/?name="+namaPenjaga).retrieve().bodyToMono(String.class).block();
    }
}