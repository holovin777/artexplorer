package space.artexplorer.api.social;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Social")

@Table(
        name = "social",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "social_title_unique",
                        columnNames = "title"
                ),
                @UniqueConstraint(
                        name = "social_link_unique",
                        columnNames = "link"
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
                name="link",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String link;

        @Column(
                name = "description",
                columnDefinition = "TEXT"
        )
        private String description;

        public Social(String title, String link, String description) {
                this.title = title;
                this.link = link;
                this.description = description;
        }

        public Social(String title, String link) {
                this.title = title;
                this.link = link;
        }

        public Social(){}

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

        public String getLink() {
                return link;
        }

        public void setLink(String link) {
                this.link = link;
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
                Social social = (Social) o;
                return Objects.equals(id, social.id) && Objects.equals(title, social.title) && Objects.equals(link, social.link) && Objects.equals(description, social.description);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, title, link, description);
        }

        @Override
        public String toString() {
                return "Social{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", link='" + link + '\'' +
                        ", description='" + description + '\'' +
                        '}';
        }
}