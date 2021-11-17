package apap.tutorial.cineplux.controller;

import apap.tutorial.cineplux.model.BioskopModel;
import apap.tutorial.cineplux.model.PenjagaModel;
import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.repository.UserDB;
import apap.tutorial.cineplux.service.RoleService;
import apap.tutorial.cineplux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDB userDB;

    @GetMapping("/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value="/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping(value="/view-all")
    private String viewAllUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString().replace("[", "").replace("]","");
        System.out.println("rolenya "+role);
        if (role.equalsIgnoreCase("ADMIN")){
            model.addAttribute("role", role);
            List<UserModel> listUser = userDB.findAll();
            model.addAttribute("listUser", listUser);
            return "viewall-user";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, Model model) {
        UserModel user = userDB.findByUsername(username);
        userService.deleteUser(user);
        return "delete-user";
    }

    @GetMapping("/ubah-password")
    public String ubahPasswordForm() {
        return "ubah-password-form";
    }

    @PostMapping("/ubah-password")
    public String ubahPasswordSubmit(
            @RequestParam("passwordLama") String passwordLama,
            @RequestParam("passwordBaru") String passwordBaru,
            @RequestParam("passwordKonfirmasi") String passwordKonfirmasi,
            Model model
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userDB.findByUsername(auth.getName());
        String response = userService.updatePassword(user,passwordLama,passwordBaru,passwordKonfirmasi);
        model.addAttribute("response", response);
        return "ubah-password";
    }
}
