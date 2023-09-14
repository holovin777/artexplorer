package space.artexplorer.api.background;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import space.artexplorer.api.laboratory.Laboratory;

import java.util.Objects;

@Entity(name = "Background")
@Table(
        name = "background",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "background_url_unique",
                        columnNames = "url"
                ),
                @UniqueConstraint(
                        name = "background_laboratory_id_key",
                        columnNames = "laboratory_id"
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
            name = "url",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String url;

    @Column(
            name = "text_color",
            columnDefinition = "TEXT"
    )
    private String textColor;

    @Column(
            name = "title_text_color",
            columnDefinition = "TEXT"
    )
    private String titleTextColor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "laboratory_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "laboratory_background_id_fk"),
            unique = true
    )
    @JsonBackReference
    private Laboratory laboratory;

    public Background(String url, String textColor, String textTitleColor, Laboratory laboratory) {
        this.url = url;
        this.textColor = textColor;
        this.titleTextColor = textTitleColor;
        this.laboratory = laboratory;
    }

    public Background(Long id, String url, String textColor, Laboratory laboratory) {
        this.url = url;
        this.textColor = textColor;
        this.laboratory = laboratory;
    }

    public Background(Long id, String url, String textColor) {
        this.url = url;
        this.textColor = textColor;
    }

    public Background(Long id, String url) {
        this.url = url;
    }

    public Background() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTextColor() {
        return this.textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Laboratory getLaboratory() {
        return this.laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public String getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(String titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Background that = (Background) o;
        return Objects.equals(id, that.id) && Objects.equals(url, that.url) && Objects.equals(textColor, that.textColor) && Objects.equals(titleTextColor, that.titleTextColor) && Objects.equals(laboratory, that.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, textColor, titleTextColor, laboratory);
    }

    @Override
    public String toString() {
        return "Background{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", textColor='" + textColor + '\'' +
                ", titleTextColor='" + titleTextColor + '\'' +
                ", laboratory=" + laboratory +
                '}';
    }
}
