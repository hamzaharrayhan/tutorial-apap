package apap.tugas.BOBAXIXIXI.controller;


import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.StoreBobaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.repository.StoreDB;
import apap.tugas.BOBAXIXIXI.service.BobaTeaService;
import apap.tugas.BOBAXIXIXI.service.ManagerService;
import apap.tugas.BOBAXIXIXI.service.StoreBobaService;
import apap.tugas.BOBAXIXIXI.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {


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

    @GetMapping("/store")
    private String store(Model model) {
        List<StoreModel> listStore = storeService.getStoreList();
        model.addAttribute("listStore", listStore);
        return "store";
    }

    @GetMapping("/store/add")
    public String addStore(Model model) {
        model.addAttribute("store", new StoreModel());
        model.addAttribute("listManager", managerService.getManagerList());
        return "form-add-store";
    }

    @PostMapping("/store/add")
    public String addStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ) {
        storeService.addStore(store);
        model.addAttribute("store", store);
        return "add-store";
    }

    @GetMapping("/store/view/{idStore}")
    public String viewDetailStore(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<BobaTeaModel> listBoba = new ArrayList<>();
        for (int i = 0; i < store.getStoreBoba().size(); i++) {
            listBoba.add(store.getStoreBoba().get(i).getBoba());
        }
        model.addAttribute("listBoba", listBoba);
        model.addAttribute("store", store);
        return "detail-store";
    }

    @GetMapping("/store/update/{idStore}")
    public String updateStoreForm(
            @PathVariable Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        model.addAttribute("store", store);
        model.addAttribute("listManager", managerService.getManagerList());
        return "form-update-store.html";
    }

    @PostMapping("/store/update")
    public String updateStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ) {
        StoreModel storeModel = storeService.getStoreByIdStore(store.getIdStore());
        if (storeService.checkErrorTime(storeModel)){
            model.addAttribute("errorTime", storeService.checkErrorTime(store));
        } else {
            storeService.updateStore(store);
        }
        model.addAttribute("store", store);
        return "update-store.html";
    }

    @RequestMapping("/store/delete/{idStore}")
    public String hapusStore(
            @PathVariable(value = "idStore", required = true) Long idStore,
            Model model
    ) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        String errorMessage;
        if (store.getStoreBoba().size() != 0){
            errorMessage = "Store still have Boba Tea and can't be ";
        } if (storeService.checkErrorTime(store)) {
            errorMessage = "Store is still open and can't be deleted";
        } else {
            errorMessage = null;
            storeService.deleteStore(idStore);
        }
        model.addAttribute("store", store);
        model.addAttribute("errorMessage", errorMessage);
        return "delete-store";
    }

    @GetMapping("/store/{idStore}/assign-boba")
    public String formAssignStore(
            @PathVariable Long idStore,
            Model model
    ){
        Boolean[] checked = new Boolean[bobaTeaService.getBobaTeaList().size()];
        Arrays.fill(checked, Boolean.FALSE);
        StoreModel store = storeService.getStoreByIdStore(idStore);
        int count = 0;
        for (BobaTeaModel boba : bobaTeaService.getBobaTeaList()){
            for (StoreBobaModel sb : store.getStoreBoba()){
                if (sb.getStore().equals(store)){
                    checked[count] = true;
                }
            }
            count++;
        }
        model.addAttribute("checked", checked);
        List<BobaTeaModel> bobaList = bobaTeaService.getBobaTeaList();
        model.addAttribute("store", store);
        model.addAttribute("bobaList", bobaList);
        return "assign-store-boba-form";
    }
    @PostMapping("/store/{idStore}/assign-boba")
    public String assignBoba(
            @ModelAttribute StoreBobaModel bobaStore,
            @PathVariable Long idStore,
            @RequestParam(value="boba")Long[] bobaList,
            Model model
    ){
        List<BobaTeaModel> newBobaList = new ArrayList<BobaTeaModel>();
        StoreModel store = storeService.getStoreByIdStore(idStore);
        for (Long bobaItem:bobaList) {
            newBobaList.add(bobaTeaService.getBobaTeaByIdBobaTea(bobaItem));
            StoreBobaModel storeBoba = new StoreBobaModel();
            storeBoba.setStore(store);
            storeBoba.setBoba(bobaTeaService.getBobaTeaByIdBobaTea(bobaItem));
            storeBobaService.addStoreBoba(storeBoba);
        }
        model.addAttribute("bobaList", newBobaList);
        model.addAttribute("store", store);
        return "assign-store-boba";
    }
}
