package none.service;
import none.entity.User;

public interface UserService {
  User register(String username, String plainPassword, String fullName, int roleId) throws Exception;
  User login(String username, String plainPassword);
}
