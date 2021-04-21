package per.pqr.jpa;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;

/**
 * Student
 *
 * @author pangqirong
 * @date 2021/4/12
 */
@Setter
@Getter
@Entity
public class Student extends PanacheEntity {
    @Schema(title = "name of student")
    private String name;
    @Schema(title = "age of student")
    private int age;
}
