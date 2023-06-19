package space.artexplorer.api.photo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.Objects;

@Entity(name = "Photo")
@Table(
        name = "photo",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "photo_name_unique",
                        columnNames = "url"
                )
        }
)
public class Photo {
    @Id
    @SequenceGenerator(
            name = "photo_id_sequence",
            sequenceName = "photo_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "photo_id_sequence"
    )
    private Long id;

    @Column(
            name = "url",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String url;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "laboratory_id_photo_fk")
    )
    private Laboratory laboratory;

    public Photo(String url) {
        this.url = url;
    }

    public Photo(Laboratory laboratory, String url) {
        this.laboratory = laboratory;
        this.url = url;
    }

    public Photo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public Laboratory getLaboratory() {
        return this.laboratory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(url, photo.url) && Objects.equals(laboratory, photo.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, laboratory);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", laboratory=" + laboratory +
                '}';
    }

}
