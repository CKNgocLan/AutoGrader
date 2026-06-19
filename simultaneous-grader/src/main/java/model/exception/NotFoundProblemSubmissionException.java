package model.exception;

import java.util.function.Supplier;

import common.message.ExceptionMessage;
import model.component.Student;

public class NotFoundProblemSubmissionException extends Exception {
	private static final long serialVersionUID = 8238765061354147623L;

	public NotFoundProblemSubmissionException(String message) {
		super(message);
	}

	public static Supplier<NotFoundProblemSubmissionException> toSupplier(Student student, String problemName) {
		return () -> new NotFoundProblemSubmissionException(ExceptionMessage.PROBLEM_SUBMISSION_OF_STUDENT_NOT_FOUND.getContent(student.fullName(), problemName));
	}
}
