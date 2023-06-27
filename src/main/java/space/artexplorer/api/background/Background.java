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

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "laboratory_id_photo_fk")
    )
    @JsonBackReference
    private Laboratory laboratory;

}
