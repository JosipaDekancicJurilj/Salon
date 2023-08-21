package ba.sum.fpmoz.salon.repositories;

import ba.sum.fpmoz.salon.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {}