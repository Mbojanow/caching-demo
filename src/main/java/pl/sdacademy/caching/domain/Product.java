package pl.sdacademy.caching.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String category;

  private String referenceNumber;
}
