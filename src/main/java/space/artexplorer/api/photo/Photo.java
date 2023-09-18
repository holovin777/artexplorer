package space.artexplorer.api.photo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import space.artexplorer.api.laboratory.Laboratory;
import space.artexplorer.api.methods.StringManipulatorUtils;

import java.util.Objects;

@Entity(name = "Photo")
@Table(
        name = "photo",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "photo_url_unique",
                        columnNames = "url"
                )
        }
)
public class Photo {
    @Id
    private String id;

    @Column(
            name = "url",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String url;

    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "laboratory_photo_id_fk")
    )
    @JsonBackReference
    private Laboratory laboratory;

    @Column(
            name = "sequence"
    )
    private int sequence;

    public Photo(String url) {
        this.url = url;
    }

    public Photo(Laboratory laboratory, String url) {
        this.laboratory = laboratory;
        this.url = url;
    }

    public Photo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @PrePersist
    public void generateIdFromUrl() {
        if (this.id == null && this.url != null) {
            this.id = StringManipulatorUtils.generateStringPhotoId(this.url);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return sequence == photo.sequence && Objects.equals(id, photo.id) && Objects.equals(url, photo.url) && Objects.equals(laboratory, photo.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, laboratory, sequence);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", laboratory=" + laboratory +
                ", sequence=" + sequence +
                '}';
    }

}