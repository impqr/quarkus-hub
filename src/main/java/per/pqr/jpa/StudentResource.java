package per.pqr.jpa;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * StudentResource
 *
 * @author pangqirong
 * @date 2021/4/13
 */
@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {
    @GET
    @Path("/{id}")
    public Student get(@PathParam("id") Long id) {
        return Student.findById(id);
    }

    @Transactional
    @POST
    public Student add(Student student) {
        // Student.persist(student);
        student.persist();
        return student;
    }
}
