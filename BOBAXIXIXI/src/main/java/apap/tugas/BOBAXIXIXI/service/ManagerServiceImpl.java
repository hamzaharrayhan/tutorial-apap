package apap.tugas.BOBAXIXIXI.service;


import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.repository.ManagerDB;
import apap.tugas.BOBAXIXIXI.repository.StoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDB managerDB;

    @Override
    public void addManager(ManagerModel manager){
        managerDB.save(manager);
    }
    @Override
    public void updateManager(ManagerModel manager){
        managerDB.save(manager);
    }

    @Override
    public void deleteManager(Long idManager){
        Optional<ManagerModel> manager = managerDB.findByIdManager(idManager);
        if(manager.isPresent()){
            managerDB.deleteById(idManager);
        }
    }

    @Override
    public List<ManagerModel> getManagerList(){
        return managerDB.findAll(Sort.by("fullName").ascending());
    }

    @Override
    public ManagerModel getManagerByIdManager(Long idManager){
        Optional<ManagerModel> manager = managerDB.findByIdManager(idManager);
        if(manager.isPresent()){
            return manager.get();
        }
        return null;
    }




}
