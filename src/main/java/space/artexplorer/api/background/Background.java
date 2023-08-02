package space.artexplorer.api.background;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.Objects;

@Entity(name = "Background")
@Table(
        name = "background",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "background_name_unique",
                        columnNames = "name"
                )
        }
)
public class Background {

    @Id
    @SequenceGenerator(
            name = "background_id_sequence",
            sequenceName = "background_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "background_id_sequence"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "laboratory_id_photo_fk")
    )
    @JsonBackReference
    private Laboratory laboratory;

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

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Background that = (Background) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(laboratory, that.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, laboratory);
    }

    @Override
    public String toString() {
        return "Background{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", laboratory=" + laboratory +
                '}';
    }

}
