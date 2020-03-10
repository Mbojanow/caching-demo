package pl.sdacademy.caching.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.caching.domain.Product;
import pl.sdacademy.caching.exception.SdaException;
import pl.sdacademy.caching.repository.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductCrudService {

  private final ProductRepository productRepository;

  public ProductCrudService(final ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Cacheable(value = "products", /*key = "#root.method"*/ keyGenerator = "allProductsKeyGenerator", unless = "#result.size() == 0 or #result.size() > 5")
  public List<Product> getAllProducts() {
    return productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
  }

  @Cacheable(value = "default", key = "#id")
  public Product getById(final Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new SdaException("Product with given id does not exist: " + id));
  }

  @Caching(
      evict = {
          @CacheEvict(cacheNames = "products", allEntries = true),
          @CacheEvict(value = "default", allEntries = true)
      }
  )
  public Product create(final Product product) {
    return productRepository.save(product);
  }

  @Caching(
      evict = {
          @CacheEvict(cacheNames = "products", allEntries = true),
          @CacheEvict(value = "default", key = "#id")
      }
  )
  public Product update(final Product product, final Long id) {
    return productRepository.findById(id).map(existingProduct -> {
      product.setId(id);
      return productRepository.save(product);
    }).orElseThrow(() -> new SdaException("Cannot update product because it does not exist"));
  }

  @Caching(
      evict = {
          @CacheEvict(cacheNames = "products", allEntries = true),
          @CacheEvict(value = "default", key = "#id")
      }
  )
  public void delete(final Long id) {
    productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
      throw new SdaException("Cannot delete non existing product");
    });
  }

}
