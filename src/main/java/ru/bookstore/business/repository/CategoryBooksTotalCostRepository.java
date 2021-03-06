package ru.bookstore.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bookstore.business.entity.CategoryBooksTotalCost;

@Repository
public interface CategoryBooksTotalCostRepository extends JpaRepository<CategoryBooksTotalCost, Long> {

}
