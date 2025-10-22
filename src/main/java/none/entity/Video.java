package none.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="Videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video {
  @Id @Column(name="VideoId")
  private String videoId;     // dùng chuỗi

  @Column(name="Active")
  private boolean active;

  @Column(name="Description", columnDefinition="NVARCHAR(1000)")
  private String description;

  @Column(name="Poster")
  private String poster;

  @Column(name="Title", columnDefinition="NVARCHAR(1000)")
  private String title;

  @Column(name="Views")
  private int views;

  @ManyToOne
  @JoinColumn(name="CategoryId")
  private Category category;
}
