package model.service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import common.constant.ProblemName;
import model.component.Student;
import model.component.StudentList;
import model.resultReport.ProblemResult;

/*
 * Student Thread Pool
 */
public class StudentThreadPool {
	private Student studentDirectory;
	private ExecutorService executorService;
	private String topic;

	public StudentThreadPool(String topic, File studentDir) {
		this.topic = topic;
		this.studentDirectory = StudentList.findByStudentDirectory(studentDir);
		this.executorService = Executors.newFixedThreadPool(ProblemName.getProblems(this.topic).size());
	}

	public Future<ProblemResult> submitTask(ProblemGradingTask task) {
		return this.executorService.submit(task);
	}

	public void shutdownService() {
		this.executorService.shutdown();
	}
}
