package none.utils;
import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {
  private PasswordUtil(){}
  public static String hash(String plain){ return BCrypt.hashpw(plain, BCrypt.gensalt(10)); }
  public static boolean matches(String plain, String hashed){
    return plain!=null && hashed!=null && BCrypt.checkpw(plain, hashed);
  }
}
