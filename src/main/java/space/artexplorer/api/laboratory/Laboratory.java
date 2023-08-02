package space.artexplorer.api.laboratory;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import space.artexplorer.api.background.Background;
import space.artexplorer.api.category.Category;
import space.artexplorer.api.photo.Photo;

import java.util.*;


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

        @OneToMany(
                mappedBy = "laboratory",
                cascade = {CascadeType.ALL}
        )
        @JsonManagedReference
        private List<Photo> photos = new ArrayList<>();

        @OneToOne(
                mappedBy = "laboratory",
                cascade = {CascadeType.ALL}
        )
        @JsonManagedReference
        private Background background;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(
                name = "collaboratorium",
                joinColumns = @JoinColumn(
                        name = "laboratory_id",
                        foreignKey = @ForeignKey(name = "collaboratorium_laboratory_id_fk")
                ),

                inverseJoinColumns = @JoinColumn(
                        name = "category_id",
                        foreignKey = @ForeignKey(name = "collaboratorium_category_id_fk")
                )
        )
        private Set<Category> categories = new HashSet<>();

        public Laboratory(String title, String description) {
                this.title = title;
                this.description = description;
        }

        public Laboratory() {
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getId() {
                return id;
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
                return this.photos;
        }

        public void setPhoto(Photo photo) {
                if (!this.photos.contains(photo)) {
                        this.photos.add(photo);
                        photo.setLaboratory(this);
                }
        }

        public void deletePhoto(Photo photo) {
                if (this.photos.contains(photo)) {
                        this.photos.remove(photo);
                        photo.setLaboratory(null);
                }
        }

        public Background getBackground() {
                return this.background;
        }

        public void setBackground(Background background) {
                this.background = background;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Laboratory that = (Laboratory) o;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(photos, that.photos) && Objects.equals(background, that.background);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, description, photos, background);
        }

        @Override
        public String toString() {
                return "Laboratory{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", photos=" + photos +
                        ", background=" + background +
                        '}';
        }
}