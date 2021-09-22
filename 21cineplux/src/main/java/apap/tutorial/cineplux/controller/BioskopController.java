package apap.tutorial.cineplux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.service.BioskopService;

import java.util.List;

@Controller
public class BioskopController {

    @Autowired
    private BioskopService bioskopService;

    // Routing URL yang diinginkan
    @RequestMapping("/bioskop/add")
    public String addBioskop(
            //Request parameter yang ingin digunakan
            @RequestParam(value = "idBioskop", required = true) String idBioskop,
            @RequestParam(value = "namaBioskop", required = true) String namaBioskop,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "noTelepon", required = true) String noTelepon,
            @RequestParam(value = "jumlahStudio", required = true) int jumlahStudio,
            Model model
    ) {
        //Membuat objek BioskopModel
        BioskopModel bioskopModel = new BioskopModel(idBioskop, namaBioskop, alamat, noTelepon, jumlahStudio);

        //Menambahkan objek BioskopModel ke dalam service
        bioskopService.addBioskop(bioskopModel);

        //Add variabel id bioskop ke "idBioskop" untuk dirender ke dalam thymeleaf
        model.addAttribute("idBioskop", idBioskop);

        //Return view template yang digunakan
        return "add-bioskop";
    }
    @RequestMapping("/bioskop/viewall")
    public String listBioskop(Model model) {
        List<BioskopModel> listBioskop = bioskopService.getBioskopList();

        model.addAttribute("listBioskop", listBioskop);
        return "viewall-bioskop";
    }

    @RequestMapping("/bioskop/view")
    public String detailBioskop(
            @RequestParam(value = "idBioskop", required = true) String idBioskop,
            Model model
    ) {
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
        model.addAttribute("bioskop", bioskopModel);

        return "view-bioskop";
    }

    @RequestMapping("/bioskop/view/id-bioskop/{idBioskop}")
    public String detailBioskopById(
            @PathVariable(value = "idBioskop", required = true) String idBioskop,
            Model model
    ){
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
        model.addAttribute("bioskop", bioskopModel);

        return "view-bioskop-byid";
    }

    @RequestMapping("/bioskop/update/id-bioskop/{idBioskop}/jumlah-studio/{updated}")
    public String updateJumlahStudio(
            @PathVariable(value = "idBioskop", required = true) String idBioskop,
            @PathVariable(value = "updated", required = true) int jumlahStudio,
            Model model
    ){
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
        bioskopModel.setJumlahStudio(jumlahStudio);
        model.addAttribute("bioskop", bioskopModel);

        return "jumlah-studio-updated";
    }

    @RequestMapping("/bioskop/delete/id-bioskop/{idBioskop}")
    public String deleteBioskop(
            @PathVariable(value = "idBioskop", required = true) String idBioskop,
            Model model
    ){
        BioskopModel bioskopModel = bioskopService.getBioskopByIdBioskop(idBioskop);
        bioskopService.getBioskopList().remove(bioskopModel);
        model.addAttribute("bioskop", bioskopModel);

        return "delete-bioskop";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingParams(MissingServletRequestParameterException ex) {
        return "error";
        // Actual exception handling
    }
}
