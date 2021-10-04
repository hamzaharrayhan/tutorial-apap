//package apap.tutorial.cineplux.service;
//
//import apap.tutorial.cineplux.model.BioskopModel;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BioskopInMemoryService implements BioskopService{
//    private List<BioskopModel> listBioskop;
//
//    //constructor
//    public BioskopInMemoryService(){
//        listBioskop = new ArrayList<>();
//    }
//
//    @Override
//    public void addBioskop(BioskopModel bioskop){
//        listBioskop.add(bioskop);
//    }
//
//    @Override
//    public List<BioskopModel> getBioskopList() {
//        return listBioskop;
//    }
//
//    @Override
//    public BioskopModel getBioskopByIdBioskop(String idBioskop) {
//        List<BioskopModel> bioskopList = getBioskopList();
//        for (BioskopModel bioskop:bioskopList) {
//            if (bioskop.getIdBioskop().equals(idBioskop)) {
//                return bioskop;
//            }
//        }
//        return null;
//    }
//}
package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.BioskopModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BioskopInMemoryService implements BioskopService{
    private List<BioskopModel> listBioskop;

    //constructor
    public BioskopInMemoryService(){
        listBioskop = new ArrayList<>();
    }

    @Override
    public void addBioskop(BioskopModel bioskop){
        listBioskop.add(bioskop);
    }

    @Override
    public List<BioskopModel> getBioskopList() {
        return listBioskop;
    }

    @Override
    public BioskopModel getBioskopByIdBioskop(String idBioskop) {
        List<BioskopModel> bioskopList = getBioskopList();
        for (BioskopModel bioskop:bioskopList) {
            if (bioskop.getIdBioskop().equals(idBioskop)) {
                return bioskop;
            }
        }
        return null;
    }
}
