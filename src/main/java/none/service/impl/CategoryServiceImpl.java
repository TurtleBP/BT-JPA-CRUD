package none.service.impl;

import none.dao.impl.CategoryDaoImpl;
import none.entity.Category;
import none.service.ICategoryService;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
  private final CategoryDaoImpl dao = new CategoryDaoImpl();
  @Override public void insert(Category c){ dao.insert(c); }
  @Override public void update(Category c){ dao.update(c); }
  @Override public void delete(Integer id){ dao.delete(id); }
  @Override public Category findById(Integer id){ return dao.findById(id); }
  @Override public List<Category> findAll(){ return dao.findAll(); }
}
