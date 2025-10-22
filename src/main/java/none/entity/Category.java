package none.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="Category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="CategoryId")
  private Integer id;

  @Column(columnDefinition="NVARCHAR(255)")
  private String name;

  private Integer status;

  @Column(columnDefinition="NVARCHAR(1000)")
  private String images;

  private String code;

  @OneToMany(mappedBy = "category")
  private List<Video> videos;
}
