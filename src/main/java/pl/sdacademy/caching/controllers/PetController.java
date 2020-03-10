package pl.sdacademy.caching.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.caching.domain.Pet;
import pl.sdacademy.caching.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

  private final PetService petService;

  public PetController(final PetService petService) {
    this.petService = petService;
  }

  @GetMapping("/{name}")
  public Pet findById(@PathVariable final String name) {
    return petService.getById(name);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody final Pet pet) {
    petService.create(pet);
  }
}
