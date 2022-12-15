package com.vitor.coursews;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vitor.coursews.model.Course;

@Consumes("application/xml,application/json")
@Produces("application/xml,application/json")
@Service
public class CourseServiceImpl implements CourseService {

	private long nextId = 1;
	private Map<Long, Course> courses = new HashMap<>();

	public CourseServiceImpl() {
		init();
	}

	private void init() {
		Course initialCourse = new Course();
		initialCourse.setId(nextId++);
		initialCourse.setName("Mastering Java");
		initialCourse.setPrice(25.00);
		initialCourse.setRating(5);
		initialCourse.setTaughtBy("Bharath Thippireddy");

		courses.put(initialCourse.getId(), initialCourse);
	}

	@Override
	public List<Course> listAll() {
		Collection<Course> results = this.courses.values();
		return new ArrayList<Course>(results);
	}

	@Override
	public Course getById(Long id) {
		return this.courses.get(id);
	}

	@Override
	public Response create(Course course) {
		
		course.setId(nextId++);
		courses.put(course.getId(), course);
		
		return Response.ok(course).build();
	}

	@Override
	public Response update(Course course) {
		Course courseToUpdate = this.courses.get(course.getId());
		
		if(courseToUpdate == null)
			return Response.notModified().build();
		
		BeanUtils.copyProperties(course, courseToUpdate);
		
		return Response.ok(course).build();
	}

	@Override
	public Response delete(Long id) {
		Course courseToDelete = this.courses.get(id);
		
		if(courseToDelete != null) {
			this.courses.remove(id);
			return Response.ok().build();
		}
		
		return Response.notModified().build();		
	}

}
