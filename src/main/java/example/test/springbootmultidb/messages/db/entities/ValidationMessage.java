package example.test.springbootmultidb.messages.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "validation_msgs")
public class ValidationMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name="message_generator", sequenceName = "message_seq", allocationSize=1)
    @Column(name="id", unique=true, nullable=false)
    Long id;

    @Column(name = "app_name")
    String appName;

    @Column(name = "msg_key")
    String key;

    @Column(name = "msg_value")
    String value;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "locale_id")
    Locale locale;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "created_ts")
    LocalDateTime createdDateTime;

    @Column(name = "updated_by")
    String updatedBy;

    @Column(name = "updated_ts")
    LocalDateTime updatedDateTime;

    /*public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }*/
}
