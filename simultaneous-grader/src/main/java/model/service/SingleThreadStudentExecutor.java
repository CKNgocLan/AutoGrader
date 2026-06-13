package model.service;

import java.io.File;
import java.util.concurrent.ExecutorService;

import org.apache.poi.ss.formula.eval.NotImplementedException;

import common.constant.TopicName;
import common.message.ExceptionMessage;
import model.component.Student;
import model.component.StudentList;

/*
 * Student Thread Pool
 */
public abstract class SingleThreadStudentExecutor implements StudentService {
	private Student student;

	public SingleThreadStudentExecutor(File studentDir) {
		this.student = StudentList.findByStudentDirectory(studentDir);
	}

	public ExecutorService toExecutor() {
		// TODO unimplemented
		throw new NotImplementedException(ExceptionMessage.NOT_IMPLEMENTED.getContent(this.getClass().getName()));
//		return Executors.newSingleThreadExecutor();
	}
}
