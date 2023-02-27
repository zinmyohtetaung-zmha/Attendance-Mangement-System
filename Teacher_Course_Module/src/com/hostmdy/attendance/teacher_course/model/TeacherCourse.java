package com.hostmdy.attendance.teacher_course.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TeacherCourse {
	
	private static final TeacherCourse INSTANCE = new TeacherCourse();
	
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty teacherId;
	private SimpleIntegerProperty courseId;
	private SimpleStringProperty academicYear;
	private SimpleStringProperty teacherName;
	private SimpleStringProperty course;
	private SimpleStringProperty courseCode;
//	private Teacher teacher;
//	private Course course;
	
	public TeacherCourse() {};
	
	public TeacherCourse(int id, int teacherId, int courseId, String academicYear) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.teacherId = new SimpleIntegerProperty(teacherId);
		this.courseId = new SimpleIntegerProperty(courseId);
		this.academicYear = new SimpleStringProperty(academicYear);
	}
	
	public TeacherCourse(int teacherId, int courseId,String academicYear) {
		super();
		this.teacherId=new SimpleIntegerProperty(teacherId);
		this.courseId = new SimpleIntegerProperty(courseId);
		this.academicYear = new SimpleStringProperty(academicYear);
	}
	
	public TeacherCourse(int id, String teacherName, String course, String academicYear, String courseCode) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.teacherName = new  SimpleStringProperty(teacherName);
		this.course = new SimpleStringProperty(course);
		this.academicYear = new SimpleStringProperty(academicYear);
		this.courseCode = new SimpleStringProperty(courseCode);
	}
	
	public TeacherCourse(String teacherName, String course, String academicYear) {
		super();
		this.teacherName = new  SimpleStringProperty(teacherName);
		this.course = new SimpleStringProperty(course);
		this.academicYear = new SimpleStringProperty(academicYear);
	}
	
	
	
//	public TeacherCourse(int id, int teacherId, int courseId, String academicYear, Teacher teacher, Course course) {
//		super();
//		this.id = new SimpleIntegerProperty(id);
//		this.teacherId = new SimpleIntegerProperty(teacherId);
//		this.courseId = new SimpleIntegerProperty(courseId);
//		this.academicYear = new SimpleStringProperty(academicYear);
//		this.teacher = teacher;
//		this.course = course;
//
//	}
//	
//	public TeacherCourse(int teacherId, int courseId,String academicYear, Teacher teacher, Course course) {
//		super();
//		this.teacherId=new SimpleIntegerProperty(teacherId);
//		this.courseId = new SimpleIntegerProperty(courseId);
//		this.academicYear = new SimpleStringProperty(academicYear);
//		this.teacher = teacher;
//		this.course = course;
//
//	}

	public static TeacherCourse getInstance() {
		return INSTANCE;
	}

	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public int getTeacherId() {
		return teacherId.get();
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = new SimpleIntegerProperty(teacherId);
	}
	public int getCourseId() {
		return courseId.get();
	}
	public void setCourseId(int courseId) {
		this.courseId = new SimpleIntegerProperty();
	}
	public String getAcademicYear() {
		return academicYear.get();
	}
	public void setYear(String academicYear) {
		this.academicYear = new SimpleStringProperty(academicYear);
	}

	public String getTeacherName() {
		return teacherName.get();
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = new SimpleStringProperty(teacherName);
	}

	public String getCourse() {
		return course.get();
	}

	public void setCourse(String course) {
		this.course = new SimpleStringProperty(course);
	}

	public String getCourseCode() {
		return courseCode.get();
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = new SimpleStringProperty(courseCode);
	}

	@Override
	public String toString() {
		return "TeacherCourse [id=" + id + ", teacherId=" + teacherId + ", courseId=" + courseId + ", academicYear="
				+ academicYear + ", teacherName=" + teacherName + ", course=" + course + ", courseCode=" + courseCode
				+ "]";
	}
	
	
	
//	public Teacher getTeacher() {
//		return teacher;
//	}
//
//	public void setTeacher(Teacher teacher) {
//		this.teacher = teacher;
//	}
//
//	public Course getCourse() {
//		return course;
//	}
//
//	public void setCourse(Course course) {
//		this.course = course;
//	}
	
	
	
	
}
