package ba.sum.fpmoz.salon.repositories;

import ba.sum.fpmoz.salon.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {}