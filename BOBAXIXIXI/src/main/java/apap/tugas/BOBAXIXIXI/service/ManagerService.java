package apap.tugas.BOBAXIXIXI.service;

import apap.tugas.BOBAXIXIXI.model.ManagerModel;

import java.util.List;

public interface ManagerService  {
    void addManager(ManagerModel manager);
    void updateManager(ManagerModel manager);
    void deleteManager(Long idManager);
    List<ManagerModel> getManagerList();
    ManagerModel getManagerByIdManager(Long idManager);

}
