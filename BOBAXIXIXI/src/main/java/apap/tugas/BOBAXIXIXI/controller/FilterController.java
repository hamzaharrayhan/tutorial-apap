package apap.tugas.BOBAXIXIXI.controller;


import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.ManagerModel;
import apap.tugas.BOBAXIXIXI.model.StoreBobaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilterController {


    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;

    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("storeBobaServiceImpl")
    @Autowired
    private StoreBobaService storeBobaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @GetMapping("/filter")
    private String filter(
            @RequestParam(value = "boba", required = false) String boba,
            Model model
    ) {
        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
        List<ManagerModel> listManager = managerService.getManagerList();
        List<BobaTeaModel> listBobaSame = new ArrayList<>();
        Set<String> listNamaBoba = new TreeSet<>();
        List<StoreBobaModel> listStoreBoba = new ArrayList<>();
        Set<ManagerModel> setManager = new HashSet<>();
        for (BobaTeaModel bobaItem : listBoba) {
            if (bobaItem.getName().equalsIgnoreCase(boba)){
                listBobaSame.add(bobaItem);
            }
        }
        for (BobaTeaModel bobaItem : listBoba) {
            listNamaBoba.add(bobaItem.getName());
        }
        for (BobaTeaModel bobaItem : listBobaSame) {
            for (StoreBobaModel storeBoba : bobaItem.getStoreBoba()){
                listStoreBoba.add(storeBoba);
            }
        }
        for (StoreBobaModel storeBoba : listStoreBoba) {
            if (listManager.contains(storeBoba.getStore().getManager()) && !setManager.contains(storeBoba.getStore().getManager())){
                setManager.add(storeBoba.getStore().getManager());
            }
        }
        model.addAttribute("listBoba", listBoba);
        model.addAttribute("listManager", setManager);
        for (ManagerModel manager : setManager) {
            System.out.println("nama manajernya yg diset"+manager.getFullName());
        }
        return "filter";
    }

}
