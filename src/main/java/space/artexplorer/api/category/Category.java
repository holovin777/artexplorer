package space.artexplorer.api.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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


    @ManyToMany(mappedBy = "categories")
    private Set<Laboratory> laboratories = new HashSet<>();

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

    public Set<Laboratory> getLaboratories() {
        return this.laboratories;
    }
    public void setLaboratory(Laboratory laboratory) {
        this.laboratories.add(laboratory);
    }

    public void deleteLaboratory(Laboratory laboratory) {
        this.laboratories.remove(laboratory);
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
