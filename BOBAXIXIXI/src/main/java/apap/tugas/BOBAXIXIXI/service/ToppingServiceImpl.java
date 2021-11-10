package apap.tugas.BOBAXIXIXI.service;


import apap.tugas.BOBAXIXIXI.model.ToppingModel;
import apap.tugas.BOBAXIXIXI.repository.ToppingDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class ToppingServiceImpl implements ToppingService {

    @Autowired
    ToppingDB toppingDB;

    @Override
    public void addTopping(ToppingModel topping){
        toppingDB.save(topping);
    }
    @Override
    public void updateTopping(ToppingModel Topping){
        toppingDB.save(Topping);
    }

    @Override
    public void deleteTopping(Long idTopping){
        Optional<ToppingModel> Topping = toppingDB.findByIdTopping(idTopping);
        if(Topping.isPresent()){
            toppingDB.deleteById(idTopping);
        }
    }

    @Override
    public List<ToppingModel> getToppingList(){
        return toppingDB.findAll(Sort.by("name").ascending());
    }

    @Override
    public ToppingModel getToppingByIdTopping(Long idTopping){
        Optional<ToppingModel> Topping = toppingDB.findByIdTopping(idTopping);
        if(Topping.isPresent()){
            return Topping.get();
        }
        return null;
    }




}
