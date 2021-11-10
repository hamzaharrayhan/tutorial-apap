package apap.tugas.BOBAXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "boba")
public class BobaTeaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBobaTea;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer price;

    @NotNull
    @Column(nullable = false)
    private Integer size;

    @NotNull
    @Column(nullable = false)
    private Integer ice_level;

    @NotNull
    @Column(nullable = false)
    private Integer sugar_level;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_topping", referencedColumnName = "idTopping", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToppingModel topping;

    @OneToMany(mappedBy = "boba")
    List<StoreBobaModel> storeBoba;
}