package apap.tugas.BOBAXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(
        name = "store_boba",
        uniqueConstraints = @UniqueConstraint(columnNames ={"id_store", "id_boba"})
)

public class StoreBobaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStoreBoba;

    @ManyToOne
    @JoinColumn(name = "id_store")
    StoreModel store;

    @ManyToOne
    @JoinColumn(name = "id_boba")
    BobaTeaModel boba;

    @Column(nullable = true)
    @Size(max = 12)
    private String production_code;
}