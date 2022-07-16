import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Path("courses")
public class CourseResource {
    CourseList courses_repository = new CourseList();
    GradeList grades_repository = new GradeList();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Course> getCourses(){
        return courses_repository.getCourses();
    }

    @GET @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Course getCourse(@PathParam("id") int id) {
        return courses_repository.getCourse(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addCourse(Course c, @Context UriInfo uriInfo) {
        //autoinkrement
        c.setId(c.getCounter_for_id().incrementAndGet());

        //walidacja danych -> wszystkie wypełnione
        boolean non_empty_fields = Stream.of(c.getId(), c.getName(), c.getLecturer())
                .allMatch(Objects::nonNull);
        //unikalne id
        boolean unique_id = true;
        //jezeli w kolekcji istnieje juz kurs z podanym ID
        if (courses_repository.getCourse(c.getId())!= null) {
            unique_id = false;
        }
        if (non_empty_fields && unique_id) {
            courses_repository.addCourse(c);
            URI location_uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(c.getId())).build();
            //return Response.status(Response.Status.CREATED).entity(c).build();
            return Response.created(location_uri)
                    .entity(c)
                    .build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateCourse(@PathParam("id") int index, Course c) {
        c.setId(index);
        //też dodac walidacje - wszystkei wypelnione
        courses_repository.updateCourse(c);
        return Response.status(Response.Status.NO_CONTENT).entity(c).build();
    }

    @DELETE @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteCourse(@PathParam("id") int index) {
        //courses_repository.deleteCourse(index);
        //return Response.status(Response.Status.OK).build();

        var output = courses_repository.deleteCourse(index);

        if (output!=null){
            //usuniecie ocen powiazanych z kursem
            grades_repository.deleteCourseWithGrades(index); //tutaj mamy blad
            return Response.status(Response.Status.NO_CONTENT).build();



        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }


}
