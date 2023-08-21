package ba.sum.fpmoz.salon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotBlank(message = "Molimo unesite naziv usluge.")
    String name;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = true)
    Category parent;

    @OneToMany(mappedBy = "parent")
    List<Category> categories;

    @OneToMany(mappedBy = "category")
    List<Article> articles;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
