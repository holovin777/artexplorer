package space.artexplorer.api.laboratory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;


@Entity(name = "Laboratory")
@Table(name = "laboratory")
public class Laboratory {
        @Id
        @SequenceGenerator(
                name = "laboratory_id_sequence",
                sequenceName = "laboratory_id_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "laboratory_id_sequence"
        )
        private Long id;

        @Column(
                name="title",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String title;

        @Column(
                name = "description",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String description;


        public Laboratory(String title, String description) {
                this.title = title;
                this.description = description;
        }

        public Laboratory() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Laboratory that = (Laboratory) o;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, description);
        }

        @Override
        public String toString() {
                return "Laboratory{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        '}';
        }
}