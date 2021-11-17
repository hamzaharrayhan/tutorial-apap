package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    void deleteUser(UserModel user);
    String updatePassword(UserModel user, String lama, String baru, String konfirmasi);
}
