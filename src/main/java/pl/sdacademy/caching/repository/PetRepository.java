package pl.sdacademy.caching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.caching.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, String> {
}
