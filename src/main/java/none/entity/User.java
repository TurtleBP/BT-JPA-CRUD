package none.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
//none/model/User.java
@Entity
@Table(name = "Users", schema = "dbo")   // <— đổi từ "users" sang "Users" và set schema "dbo"
@NamedQueries({
@NamedQuery(name="User.findByUsername", query="SELECT u FROM User u WHERE u.username=:username")
})
public class User {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;

@Column(nullable=false, unique=true, length=100)
private String username;

@Column(nullable=false, length=255)
private String password;

@Column(length=255)
private String fullName;

@Column(name="role_id", nullable=false)
private Integer roleId; // 1=ADMIN, 3=USER
}
