package none.service;
import none.entity.Video;
import java.util.List;

public interface IVideoService {
  void insert(Video v);
  void update(Video v);
  void delete(String id);
  Video findById(String id);
  List<Video> findAll();
}
