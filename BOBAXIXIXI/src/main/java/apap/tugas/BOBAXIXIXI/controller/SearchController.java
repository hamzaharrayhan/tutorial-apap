package apap.tugas.BOBAXIXIXI.controller;


import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
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
public class SearchController {


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

    @GetMapping("/search")
    private String search(
            @RequestParam(value = "boba", required = false) String boba,
            @RequestParam(value = "topping", required = false) String topping,
            Model model
    ) {
        List<BobaTeaModel> listBoba = bobaTeaService.getBobaTeaList();
        Set<String> bobaSet = new TreeSet<>();
        Set<StoreBobaModel> storeBobaList = new HashSet<>();
        Set<BobaTeaModel> bobaList = new HashSet<>();
        for (BobaTeaModel bobaItem : listBoba) {
            bobaSet.add(bobaItem.getName());
        }
        try {
            for (BobaTeaModel bobaItem : listBoba) {
                if (bobaItem.getName().equalsIgnoreCase(boba) && bobaItem.getTopping().getName().equalsIgnoreCase(topping)){
                    for (StoreBobaModel storeBoba : bobaItem.getStoreBoba()) {
                        if (storeService.checkErrorTime(storeBoba.getStore())){
                            storeBobaList.add(storeBoba);
                        }
                    }
                }
            }
            for (StoreBobaModel str : storeBobaList){
                bobaList.add(str.getBoba());
            }
            model.addAttribute("listBoba", bobaList);
        } catch (Exception e) {
        }
        model.addAttribute("setBoba", bobaSet);
        model.addAttribute("listTopping", toppingService.getToppingList());
        return "search";
    }

}
