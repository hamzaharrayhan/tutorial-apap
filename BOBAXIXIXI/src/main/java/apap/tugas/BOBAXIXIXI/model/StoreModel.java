package apap.tugas.BOBAXIXIXI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "store")
public class StoreModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStore;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String address;

    @Size(max = 10)
    @Column(nullable = true)
    private String storeCode;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openHour;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeHour;

    @OneToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "id_manager", referencedColumnName = "idManager", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ManagerModel manager;

    @OneToMany(mappedBy = "store")
    List<StoreBobaModel> storeBoba;
}