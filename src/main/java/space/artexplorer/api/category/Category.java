package space.artexplorer.api.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Category")
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id_sequence"
    )
    private Long id;

    @Column(
            name="name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @JsonIgnoreProperties("categories")
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "categories")
    private List<Laboratory> laboratories = new ArrayList<>();

    public Category(String name) {
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

    public List<Laboratory> getLaboratories() {
        return this.laboratories;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        this.laboratories = laboratories;
    }

    public void addLaboratory(Laboratory laboratory) {
        this.laboratories.add(laboratory);
        laboratory.getCategories().add(this);
    }

    public void deleteLaboratory(Laboratory laboratory) {
        this.laboratories.remove(laboratory);
        laboratory.getCategories().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(laboratories, category.laboratories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, laboratories);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", laboratories=" + laboratories +
                '}';
    }
}
