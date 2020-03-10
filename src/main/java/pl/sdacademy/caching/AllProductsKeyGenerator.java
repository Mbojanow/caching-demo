package pl.sdacademy.caching;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class AllProductsKeyGenerator implements KeyGenerator {

  @Override
  public Object generate(final Object target, final Method method, final Object... params) {
    return "products";
  }
}
