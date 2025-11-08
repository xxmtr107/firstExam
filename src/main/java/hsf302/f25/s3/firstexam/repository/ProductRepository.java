package hsf302.f25.s3.firstexam.repository;

import hsf302.f25.s3.firstexam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findTop3ByCate_CateIdOrderByStockDesc(@Param("categoryId") Integer category);
    List<Product> findAllByOrderByCreatedAtDesc();
}
