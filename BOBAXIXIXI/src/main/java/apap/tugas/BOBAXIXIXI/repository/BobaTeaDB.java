package apap.tugas.BOBAXIXIXI.repository;

import apap.tugas.BOBAXIXIXI.model.BobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BobaTeaDB extends JpaRepository<BobaTeaModel, Long> {
    Optional<BobaTeaModel> findByIdBobaTea(Long idBobaTea);
}
