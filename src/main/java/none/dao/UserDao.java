package none.dao;
import none.entity.User;
public interface UserDao {
  User findByUsername(String username);
}
