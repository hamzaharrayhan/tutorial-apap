package apap.tugas.BOBAXIXIXI.controller;


import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import apap.tugas.BOBAXIXIXI.model.StoreBobaModel;
import apap.tugas.BOBAXIXIXI.model.StoreModel;
import apap.tugas.BOBAXIXIXI.service.BobaTeaService;
import apap.tugas.BOBAXIXIXI.service.StoreBobaService;
import apap.tugas.BOBAXIXIXI.service.StoreService;
import apap.tugas.BOBAXIXIXI.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BobaTeaController {


    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("storeBobaServiceImpl")
    @Autowired
    private StoreBobaService storeBobaService;

    @GetMapping("/boba")
    private String boba(Model model) {
        List<BobaTeaModel> listBobaTea = bobaTeaService.getBobaTeaList();
        model.addAttribute("listBobaTea", listBobaTea);
        return "boba";
    }

    @GetMapping("/boba/add")
    public String addBobaTea(Model model) {
        model.addAttribute("boba", new BobaTeaModel());
        model.addAttribute("listTopping", toppingService.getToppingList());
        return "form-add-boba";
    }

    @PostMapping("/boba/add")
    public String addBobaSubmit(
            @ModelAttribute BobaTeaModel boba,
            Model model
    ) {
        bobaTeaService.addBobaTea(boba);
        model.addAttribute("boba", boba);
        return "add-boba";
    }

//    @GetMapping("/store/view/{idStore}")
//    public String viewDetailStore(
//            @PathVariable Long idStore,
//            Model model
//    ) {
//        StoreModel store = storeService.getStoreByIdStore(idStore);
//        model.addAttribute("listBoba", store.getStoreBoba());
//        model.addAttribute("store", store);
//        return "detail-store";
//    }

    @GetMapping("/boba/update/{idBobaTea}")
    public String updateStoreForm(
            @PathVariable Long idBobaTea,
            Model model
    ) {
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        model.addAttribute("boba", bobaTea);
        model.addAttribute("listTopping", toppingService.getToppingList());
        return "form-update-boba.html";
    }

    @PostMapping("/boba/update")
    public String updateBobaTeaSubmit(
            @ModelAttribute BobaTeaModel boba,
            Model model
    ) {
        String errorMessage;
        if (bobaTeaService.checkErrorTime(boba)){
            errorMessage = "Store(s) which selling this Boba Tea is Still open, Boba Tea can't be updated";
        } else {
            errorMessage = null;
            bobaTeaService.updateBobaTea(boba);
        }
        bobaTeaService.updateBobaTea(boba);
        model.addAttribute("boba", boba);
        model.addAttribute("errorMessage", errorMessage);
        return "update-boba.html";
    }

    @GetMapping("/boba/{idBobaTea}/assign-store")
    public String formAssignBoba(
        @PathVariable Long idBobaTea,
        Model model
    ){
        Boolean[] checked = new Boolean[storeService.getStoreList().size()];
        Arrays.fill(checked, Boolean.FALSE);
        BobaTeaModel boba = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        List<Long> idStore = new ArrayList<>();
        int count = 0;
        for (StoreModel store : storeService.getStoreList()){
            for (StoreBobaModel sb : store.getStoreBoba()){
                if (sb.getBoba().equals(boba)){
                    checked[count] = true;
                }
            }
            count++;
        }
        for (StoreBobaModel sb : boba.getStoreBoba()){
            idStore.add(sb.getIdStoreBoba());
        }
        bobaTeaService.deleteStoreList(idStore);
        model.addAttribute("checked", checked);
        List<StoreModel> storeList = storeService.getStoreList();
        model.addAttribute("boba", boba);
        model.addAttribute("storeList", storeList);
        return "assign-boba-store-form";
    }
    @PostMapping("/boba/{idBoba}/assign-store")
    public String assignBoba(
            @ModelAttribute StoreBobaModel bobaStore,
            @PathVariable Long idBoba,
            @RequestParam(value="store")Long[] storeList,
            Model model
    ){
        List<StoreModel> newStoreList = new ArrayList<StoreModel>();
        BobaTeaModel boba = bobaTeaService.getBobaTeaByIdBobaTea(idBoba);
        for (Long storeItem:storeList) {
            newStoreList.add(storeService.getStoreByIdStore(storeItem));
            StoreBobaModel storeBoba = new StoreBobaModel();
            storeBoba.setBoba(boba);
            storeBoba.setStore(storeService.getStoreByIdStore(storeItem));
            System.out.println("ini store"+storeService.getStoreByIdStore(storeItem).getName());
            storeBobaService.addStoreBoba(storeBoba);
        }
        model.addAttribute("storeList", newStoreList);
        model.addAttribute("boba", boba);
        return "assign-boba-store";
    }

    @RequestMapping("/boba/delete/{idBobaTea}")
    public String hapusBobaTea(
            @PathVariable(value = "idBobaTea", required = true) Long idBobaTea,
            Model model
    ) {
        BobaTeaModel boba = bobaTeaService.getBobaTeaByIdBobaTea(idBobaTea);
        String errorMessage;
        if (boba.getStoreBoba().size() != 0){
            errorMessage = "Store(s) is still selling this Boba Tea and can't be deleted";
        } else {
            errorMessage = null;
            bobaTeaService.deleteBobaTea(idBobaTea);
        }
        model.addAttribute("boba", boba);
        model.addAttribute("errorMessage", errorMessage);
        return "delete-boba";
    }
}
