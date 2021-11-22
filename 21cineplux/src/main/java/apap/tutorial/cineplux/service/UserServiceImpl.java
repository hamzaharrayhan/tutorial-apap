package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDB;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public void deleteUser(UserModel user){
        userDB.delete(user);
    }

    @Override
    public String updatePassword(UserModel user, String lama, String baru, String konfirmasi) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isMatch = passwordEncoder.matches(lama, user.getPassword());
        boolean sama = passwordEncoder.matches(baru, user.getPassword());

        if (isMatch == false){
            return "Password lama anda tidak sesuai";
        } else if (baru.equals(lama) == true){
            return "Password baru anda sama dengan password lama";
        } else if (isMatch && baru.equals(konfirmasi)){
            user.setPassword(passwordEncoder.encode(baru));
            userDB.save(user);
            return "Password berhasil diubah";
        } else if (baru.equals(konfirmasi) == false) {
            return "Password konfirmasi anda tidak sesuai dengan password baru";
        } else {
            return "";
        }
    }

}
