package none.service;
import none.entity.Category;
import java.util.List;

public interface ICategoryService {
  void insert(Category c);
  void update(Category c);
  void delete(Integer id);
  Category findById(Integer id);
  List<Category> findAll();
}
