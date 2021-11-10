package apap.tugas.BOBAXIXIXI.service;


import apap.tugas.BOBAXIXIXI.model.StoreModel;
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
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreDB storeDB;

    @Override
    public void addStore(StoreModel store){
        store.setStoreCode(generateStoreCode(store));
        storeDB.save(store);
    }
    @Override
    public void updateStore(StoreModel store){

        store.setStoreCode(generateStoreCode(store));
        storeDB.save(store);
    }

    @Override
    public void deleteStore(Long idStore){
        Optional<StoreModel> store = storeDB.findByIdStore(idStore);
        if(store.isPresent()){
            storeDB.deleteById(idStore);
        }
    }

    @Override
    public List<StoreModel> getStoreList(){
        return storeDB.findAll(Sort.by("idStore").ascending());
    }

    @Override
    public StoreModel getStoreByIdStore(Long idStore){
        Optional<StoreModel> store = storeDB.findByIdStore(idStore);
        if(store.isPresent()){
            return store.get();
        }
        return null;
    }

    @Override
    public String generateStoreCode(StoreModel store) {
        String nama = store.getName();
        String open = store.getOpenHour().toString();
        String close = store.getCloseHour().toString();
        String nstr = "";
        for (int i=0; i<3; i++)
        {
            char ch= nama.charAt(i); //extracts each character
            nstr = ch+nstr; //adds each character in front of the existing string
        }

        String jam = "";
        for (int i=0; i<2; i++)
        {
            char ch= open.charAt(i); //extracts each character
            jam = jam+ch; //adds each character in front of the existing string
        }
        nstr = nstr.toUpperCase() + jam;
        if (close.charAt(0) == '0') {
            nstr = nstr + "0";
        } if (close.charAt(0) == '1') {
            nstr = nstr + "1";
        } else {
            nstr = nstr + "2";
        }
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            salt.append(chars.charAt(index));
        }
        String saltStr = salt.toString();
        nstr = "SC" + nstr + saltStr;
        store.setStoreCode(nstr);
        return nstr;
    }

    @Override
    public boolean checkErrorTime(StoreModel store) {
        if (LocalTime.now().isAfter(store.getOpenHour()) && LocalTime.now().isBefore(store.getCloseHour())) {
            return true;
        }
        return false;
    }
}
