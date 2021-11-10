package apap.tugas.BOBAXIXIXI.repository;

import apap.tugas.BOBAXIXIXI.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreDB extends JpaRepository<StoreModel, Long> {
    Optional<StoreModel> findByIdStore(Long idStore);
}
