package pl.sdacademy.caching.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.sdacademy.caching.domain.Pet;
import pl.sdacademy.caching.repository.PetRepository;

@Service
@CacheConfig(cacheNames = "pets")
public class PetService {

  private final PetRepository petRepository;

  public PetService(final PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @CachePut(key = "#pet.name")
  public Pet create(final Pet pet) {
    return petRepository.save(pet);
  }

  @Cacheable(key = "#name")
  public Pet getById(final String name) {
    return petRepository.getOne(name);
  }

}
