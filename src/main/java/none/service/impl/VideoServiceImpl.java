package none.service.impl;

import none.dao.impl.VideoDao;
import none.entity.Video;
import none.service.IVideoService;
import java.util.List;

public class VideoServiceImpl implements IVideoService {
  private final VideoDao dao = new VideoDao();
  @Override public void insert(Video v){ dao.insert(v); }
  @Override public void update(Video v){ dao.update(v); }
  @Override public void delete(String id){ dao.delete(id); }
  @Override public Video findById(String id){ return dao.findById(id); }
  @Override public List<Video> findAll(){ return dao.findAll(); }
}
