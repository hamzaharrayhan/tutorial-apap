package apap.tutorial.cineplux.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.repository.RoleDB;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDB roleDB;

    @Override
    public List<RoleModel> getListRole() {
        return roleDB.findAll();
    }
}
