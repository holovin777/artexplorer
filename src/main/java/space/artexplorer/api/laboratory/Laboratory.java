package space.artexplorer.api.laboratory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import space.artexplorer.api.background.Background;
import space.artexplorer.api.category.Category;
import space.artexplorer.api.methods.StringManipulatorUtils;
import space.artexplorer.api.photo.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Laboratory")
@Table(
        name = "laboratory",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "laboratory_title_unique",
                        columnNames = "title"
                )
        }
)

public class Laboratory {
        @Id
        private String id;

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
                cascade = {CascadeType.ALL},
                orphanRemoval = true
        )
        @JsonManagedReference
        private List<Photo> photos = new ArrayList<>();

        @OneToOne(
                mappedBy = "laboratory",
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
        )
        @JsonIgnoreProperties(value = "laboratory")
        private Background background;

        @JsonIgnoreProperties("laboratories")
        @ManyToMany(
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
        )
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
        private List<Category> categories = new ArrayList<>();

        @Column(
                name = "min_age",
                columnDefinition = "INTEGER"
        )
        private Integer minAge;

        @Column(
                name = "max_age",
                columnDefinition = "INTEGER"
        )
        private Integer maxAge;

        public Laboratory(String title, String description) {
                this.title = title;
                this.description = description;
        }

        public Laboratory(String title, String description, Integer minAge, Integer maxAge) {
                this.title = title;
                this.description = description;
                this.minAge = minAge;
                this.maxAge = maxAge;
        }

        public Laboratory() {
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getId() {
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

        public void setPhotos(List<Photo> photos) {
                this.photos = photos;
        }

        public void deletePhoto(Photo photo) {
                if (this.photos.contains(photo)) {
                        this.photos.remove(photo);
                        photo.setLaboratory(null);
                }
        }

        public List<Category> getCategories() {
                return this.categories;
        }

        public void addCategory(Category category) {
                this.categories.add(category);
                category.getLaboratories().add(this);
        }

        public void deleteCategory(Category category) {
                this.categories.remove(category);
                category.getLaboratories().remove(this);
        }

        public Background getBackground() {
                return this.background;
        }

        public void setBackground(Background background) {
                this.background = background;
        }

        public void setCategories(List<Category> categories) {
                this.categories = categories;
        }

        public Integer getMinAge() {
                return this.minAge;
        }

        public void setMinAge(Integer minAge) {
                this.minAge = minAge;
        }

        public Integer getMaxAge() {
                return this.maxAge;
        }

        public void setMaxAge(Integer maxAge) {
                this.maxAge = maxAge;
        }

        @PrePersist
        public void generateIdFromTitle() {
                if (this.id == null && this.title != null) {
                        this.id = StringManipulatorUtils.generateStringId(this.title);
                }
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Laboratory that = (Laboratory) o;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(photos, that.photos) && Objects.equals(background, that.background) && Objects.equals(categories, that.categories) && Objects.equals(minAge, that.minAge) && Objects.equals(maxAge, that.maxAge);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, description, photos, background, categories, minAge, maxAge);
        }

        @Override
        public String toString() {
                return "Laboratory{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", photos=" + photos +
                        ", background=" + background +
                        ", categories=" + categories +
                        ", minAge=" + minAge +
                        ", maxAge=" + maxAge +
                        '}';
        }
}