package pl.sdacademy.caching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.caching.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
