package model.service;

import java.io.File;
import java.util.concurrent.ExecutorService;

import org.apache.poi.ss.formula.eval.NotImplementedException;

import common.constant.LabName;
import common.message.ExceptionMessage;
import model.component.Student;
import model.component.StudentList;

/*
 * Student Thread Pool
 */
public abstract class SingleThreadStudentExecutor {
	private String topic;
	private Student student;

	public SingleThreadStudentExecutor(File studentDir) {
		this.topic = studentDir.getParentFile().getName();
		this.student = StudentList.findByStudentDirectory(studentDir);
	}

	public ExecutorService toExecutor() {
		// TODO unimplemented
		throw new NotImplementedException(ExceptionMessage.NOT_IMPLEMENTED.getContent(this.getClass().getName()));
//		return Executors.newSingleThreadExecutor();
	}

	private boolean validate() {
		if (!LabName.L1.equals(topic)
				&& !LabName.L2.equals(topic)
				&& !LabName.L3.equals(topic)
				&& !LabName.L4.equals(topic)
				&& !LabName.L5.equals(topic)
				&& !LabName.L6.equals(topic)
				&& !LabName.L7.equals(topic)
				) {
			return false;
		}

		
		return true;
	}
}
