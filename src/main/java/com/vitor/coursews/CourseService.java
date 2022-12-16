package com.vitor.coursews;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.vitor.coursews.model.Course;

@Consumes("application/xml,application/json")
@Produces("application/xml,application/json")
@Path("/courseservice")
public interface CourseService {
	
	@Path("/courses")
	@GET
	List<Course> listAll();
	
	@Path("/courses/{id}")
	@GET
	Course getById(@PathParam("id") Long id);
	
	@Path("/courses")
	@POST
	Response create(Course course);
	
	@Path("/courses")
	@PUT
	Response update(Course course);
	
	@Path("/courses/{id}")
	@DELETE
	Response delete(@PathParam("id") Long id);

}
