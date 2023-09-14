package space.artexplorer.api.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static space.artexplorer.api.methods.StringManipulatorUtils.generateStringId;

@Entity(name = "Category")
@Table(
    name = "category",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "category_name_unique",
                columnNames = "name"
        )
    }
)
public class Category {

    @Id
    private String id;

    @Column(
        name="name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "name_text_color",
            columnDefinition = "TEXT"
    )
    private String nameTextColor;

    @JsonIgnoreProperties("categories")
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "categories")
    private List<Laboratory> laboratories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String nameTextColor) {
        this.name = name;
        this.nameTextColor = nameTextColor;
    }

    public Category() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getNameTextColor() {
        return nameTextColor;
    }

    public void setNameTextColor(String nameTextColor) {
        this.nameTextColor = nameTextColor;
    }

    @PrePersist
    public void generateIdFromName() {
        if (this.id == null && this.name != null) {
            this.id = generateStringId(this.name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(nameTextColor, category.nameTextColor) && Objects.equals(laboratories, category.laboratories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameTextColor, laboratories);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameTextColor='" + nameTextColor + '\'' +
                ", laboratories=" + laboratories +
                '}';
    }
}
