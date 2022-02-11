package ee.backend.api.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditFields {

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDtime;

    @UpdateTimestamp
    private LocalDateTime modifiedDtime;
}
