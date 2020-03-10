package pl.sdacademy.caching.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.caching.domain.Product;
import pl.sdacademy.caching.service.ProductCrudService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

  private final ProductCrudService productCrudService;

  public ProductController(final ProductCrudService productCrudService) {
    this.productCrudService = productCrudService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productCrudService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable final Long id) {
    return productCrudService.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product createProduct(@RequestBody final Product product) {
    return productCrudService.create(product);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateProduct(@RequestBody final Product product, @PathVariable final Long id) {
    productCrudService.update(product, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable final Long id) {
    productCrudService.delete(id);
  }
}
