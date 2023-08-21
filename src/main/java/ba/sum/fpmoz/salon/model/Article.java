package ba.sum.fpmoz.salon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name="articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotBlank(message = "Molimo unesite naziv usluge.")
    String name;

    @Column(nullable = false)
    @NotBlank(message = "Molimo unesite opis usluge.")
    String description;

    @Column(nullable = false)
    @NotNull(message = "Molimo unesite cijenu usluge.")
    Float price;


    @Column(nullable = false)
    String image;

    @NotBlank(message = "Molimo unesite jedinicu proizvoda.")
    @Column(nullable = false)
    String unit;

    @NotNull(message = "Molimo odaberite kategoriju usluge.")
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    Category category;

    @ManyToMany(mappedBy = "articles")
    List<Invoice> invoices;

    public Article() {
    }

    public Article(Long id, String name, String description, Float price, String image, String unit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.unit = unit;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
