package example.test.springbootmultidb.messages.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "locale")
public class Locale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locale_generator")
    @SequenceGenerator(name="locale_generator", sequenceName = "locale_seq", allocationSize=1)
    @Column(name="locale_id", unique=true, nullable=false)
    private Long localeId;

    @Column(name= "lang_code", nullable=false, length=2)
    private String languageCode;

    @Column(nullable=false, length=2)
    private String country;

    @OneToMany(mappedBy="locale", fetch = FetchType.LAZY)
    private Set<ValidationMessage> messages;
}
