package space.artexplorer.api.collaboratorium;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import space.artexplorer.api.category.Category;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.Objects;

@Entity(name = "Collaboratorium")
@Table(name = "collaboratorium")
public class Collaboratorium {

    @Id
    @SequenceGenerator(
            name = "collaboratorium_id_sequence",
            sequenceName = "collaboratorium_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collaboratorium_id_sequence"
    )
    private Long id;

    @ManyToOne
    private Laboratory laboratory;

    @ManyToOne
    private Category category;

    public Collaboratorium(Laboratory laboratory, Category category) {
        this.laboratory = laboratory;
        this.category = category;
    }

    public Collaboratorium() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Laboratory getLaboratory() {
        return this.laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaboratorium that = (Collaboratorium) o;
        return Objects.equals(id, that.id) && Objects.equals(laboratory, that.laboratory) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, laboratory, category);
    }

    @Override
    public String toString() {
        return "Collaboratorium{" +
                "id=" + id +
                ", laboratory=" + laboratory +
                ", category=" + category +
                '}';
    }
}

