package space.artexplorer.api.laboratory;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import space.artexplorer.api.photo.Photo;

import java.util.ArrayList;
import java.util.List;
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

        @Column(
                name = "photos"
        )
        @OneToMany(
                mappedBy = "laboratory",
                cascade = {CascadeType.REFRESH}
        )
        @JsonManagedReference
        private List<Photo> photos = new ArrayList<>();


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

        public List<Photo> getPhotos() {
                return photos;
        }

        public void addPhoto(Photo photo) {
                if (!this.photos.contains(photo)) {
                        this.photos.add(photo);
                        photo.setLaboratory(this);
                }
        }

        public void removePhoto(Photo photo) {
                if (this.photos.contains(photo)) {
                        this.photos.remove(photo);
                        photo.setLaboratory(null);
                }
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Laboratory that = (Laboratory) o;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(photos, that.photos);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, description, photos);
        }

        @Override
        public String toString() {
                return "Laboratory{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", photos=" + photos +
                        '}';
        }

}