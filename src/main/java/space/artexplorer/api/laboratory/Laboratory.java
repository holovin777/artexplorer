package space.artexplorer.api.laboratory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "Laboratory")
@Table(
        name = "laboratory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_name_unique",
                        columnNames = "name"
                )
        }
)
public class Laboratory {
        @Id
        @SequenceGenerator(
                name = "customer_id_sequence",
                sequenceName = "customer_id_sequence"
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "customer_id_sequence"
        )
        private Integer id;
        @Column(
                name="name",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String name;
        @Column(
                name = "photo"
        )
        private String photo;
}