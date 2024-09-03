package space.artexplorer.api.social;

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


@Entity(name = "Social")
@Table(
        name = "social",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "social_title_unique",
                        columnNames = "title"
                )
        }
)

public class Social {
        @Id
        @SequenceGenerator(
                name = "social_id_sequence",
                sequenceName = "social_id_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "social_id_sequence"
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
                mappedBy = "social",
                cascade = {CascadeType.ALL},
                orphanRemoval = true
        )
        @JsonManagedReference
        private List<Photo> photos = new ArrayList<>();

        @OneToOne(
                mappedBy = "social",
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
        )
        @JsonIgnoreProperties(value = "social")
        private Background background;

        @JsonIgnoreProperties("laboratories")
        @ManyToMany(
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
        )
        @JoinTable(
                name = "collaboratorium",
                joinColumns = @JoinColumn(
                        name = "social_id",
                        foreignKey = @ForeignKey(name = "collaboratorium_social_id_fk")
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

        public Social(String title, String description) {
                this.title = title;
                this.description = description;
        }

        public Social(String title, String description, Integer minAge, Integer maxAge) {
                this.title = title;
                this.description = description;
                this.minAge = minAge;
                this.maxAge = maxAge;
        }

        public Social() {
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
                        photo.setSocial(this);
                }
        }

        public void setPhotos(List<Photo> photos) {
                this.photos = photos;
        }

        public void deletePhoto(Photo photo) {
                if (this.photos.contains(photo)) {
                        this.photos.remove(photo);
                        photo.setSocial(null);
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
                Social that = (Social) o;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(photos, that.photos) && Objects.equals(background, that.background) && Objects.equals(categories, that.categories) && Objects.equals(minAge, that.minAge) && Objects.equals(maxAge, that.maxAge);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, description, photos, background, categories, minAge, maxAge);
        }

        @Override
        public String toString() {
                return "Social{" +
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