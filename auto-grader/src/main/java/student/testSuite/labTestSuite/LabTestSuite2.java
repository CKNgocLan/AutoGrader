package student.testSuite.labTestSuite;

import java.util.Arrays;
import java.util.List;

import student.constant.ClassName;
import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
import student.model.ClassLoader;
import student.model.ITestCase;
import student.model.ParameterTesting;
import student.testSuite.lab2.ConstTester;
import student.testSuite.lab2.CustomerTester;
import student.testSuite.lab2.problem1.CarTester;
import student.testSuite.lab2.problem2.TemperatureTester;
import student.testSuite.lab2.problem3.PetShopTester;
import student.testSuite.lab2.problem3.PetTester;
import student.testSuite.lab2.problem3.ServiceEstimateTester;
import student.testSuite.lab2.problem4.CakeTester;
import student.testSuite.lab2.problem4.EventTester;
import student.testSuite.lab2.problem4.QuoteTester;
import student.testSuite.lab2.problem5.AgreementTester;
import student.testSuite.lab2.problem6.BookTester;
import student.testSuite.lab2.problem6.BorrowingRecordTester;
import student.testSuite.lab2.problem6.LibraryManagementTester;
import student.testSuite.lab2.problem6.UserTester;
import student.util.ParameterTestingUtils;

/**
 * Test suite for the Employee class. Tests constructors, getters, and setters
 * as per the uploaded Employee.java
 */
public class LabTestSuite2 extends ALabTestSuite {

	@Override
	public List<ITestCase> getAllTests(String question) {
		try {
			switch (question) {
			case Question.Q1:
				CarTester car = CarTester.getInstance();

				return Arrays.asList(car.checkExistence(5)

				// partial-args constructor
						,
						car.checkPartialArgsConstructorDeclaration(5,
								ParameterTestingUtils.toArray(int.class, String.class)),
						car.checkPartialArgsConstructorOperation(10,
								new ParameterTesting(FieldName.YEAR_MODEL, int.class, 2025),
								new ParameterTesting(FieldName.MAKE, String.class, "Mazda"),
								new ParameterTesting(FieldName.SPEED, int.class, 0, true))

						// accelerate()
						, car.checkAccelerateDeclaration(25), car.checkAccelerateOperation(15)

						// brake()
						, car.checkBrakeDeclaration(25), car.checkBrakeOperation(15));
			case Question.Q2:
				TemperatureTester temperature = TemperatureTester.getInstance();

				return Arrays.asList(temperature.checkExistence(5),
						temperature.checkField(5, FieldName.FTEMP, double.class),
						temperature.checkSetgetFahrenheit(10, 50), temperature.checkGetCelsius(15, 50, 10),
						temperature.checkGetKelvin(15, 50, 283));
			case Question.Q3:
				CustomerTester customer = CustomerTester.getInstance();
				PetTester pet = PetTester.getInstance();
				ServiceEstimateTester serviceEstimate = ServiceEstimateTester.getInstance();
				PetShopTester petShop = PetShopTester.getInstance();

				return Arrays.asList(
						// customer
						customer.checkExistence(5)
						, customer.checkFields(5)
						, customer.checkNoArgsConstructors(5)
						, customer.checkGetterDeclaration(5)
						, customer.checkSetterDeclaration(5)
						, customer.checkToStringOperation(5)

						// pet
						, pet.checkExistence(5)
						, pet.checkFields(15)
						, pet.checkPartialArgsConstructors(5,
								new ParameterTesting(CustomerTester.getCorrespondingClass()))
						, pet.checkPartialArgsConstructors(10,
								new ParameterTesting(CustomerTester.getCorrespondingClass()),
								new ParameterTesting(String.class),
								new ParameterTesting(int.class),
								new ParameterTesting(double.class))
						, pet.checkToStringExistence(5)
						, pet.checkToStringOperation(5)
						, pet.checkToStringOperation(5, "H'Mong Coc", 24, 15, CustomerTester.initObject("Lao Hac"))

						// service estimate
						, serviceEstimate.checkExistence(5)
						, serviceEstimate.checkFields(15)
						, serviceEstimate.checkNoArgsConstructors(5)
						, serviceEstimate.checkGetTotalCostAfterTaxExistence(5)
						, serviceEstimate.checkGetTotalCostAfterTaxOperation(5)

						// petShop
						, petShop.checkExistence(5)
						, petShop.checkFields(15)
						, petShop.checkNoArgsConstructors(5)
						, petShop.checkAddCustomerOperation(21)
						, petShop.checkShowAllCustomersDeclaration(3)
						, petShop.checkShowAllCustomersOperation(21)
						
						, petShop.checkAddPetDeclaration(3)
						, petShop.checkAddPetOperation(9)
						, petShop.checkShowAllPetsDeclaration(3)
						, petShop.checkShowAllPetsOperation(9)
						
						, petShop.checkAddServiceEstimateDeclaration(3)
						, petShop.checkAddServiceEstimateOperation(9)
						, petShop.checkShowAllCustomersDeclaration(3)
						, petShop.checkShowAllCustomersOperation(9)
						);
			case Question.Q4:
				customer = CustomerTester.getInstance();
				CakeTester cake = CakeTester.getInstance();
				ConstTester consts = ConstTester.getInstance();
				EventTester event = EventTester.getInstance();
				QuoteTester quote = QuoteTester.getInstance();

				return Arrays.asList(
						// customer
						customer.checkExistence(5)
//						, customer.checkFields(5)
//						, customer.checkNoArgsConstructors(5)
//						, customer.checkGetterDeclaration(2)
//						, customer.checkSetterDeclaration(2)
//						, customer.checkToStringOperation(5)
						
						// const
						, consts.checkDeclaration(5)
						, consts.checkFields(5)
						
//						// event
//						, event.checkDeclaration(5)
//						, event.checkFields(5)
//						
//						// cake
//						, cake.checkExistence(5)
//						, cake.checkFields(5)
//						
//						// quote
//						, quote.checkExistence(5)
//						, quote.checkFields(5)
				);
			case Question.Q5:
				customer = CustomerTester.getInstance();
				consts = ConstTester.getInstance();
				AgreementTester agreement = AgreementTester.getInstance();
				student.testSuite.lab2.problem5.CarTester car5 = student.testSuite.lab2.problem5.CarTester.getInstance();

				return Arrays.asList(
						// existence
						customer.checkExistence(5)
						, consts.checkDeclaration(1)
						, agreement.checkExistence(5)
						, car5.checkExistence(5)
						
						// field
						, customer.checkFields(2)
						, consts.checkFields(1)
						, agreement.checkFields(5)
						, car5.checkFields(5)
						
						// getter
						, customer.checkGetterDeclaration(2)

						// setter
						, customer.checkSetterDeclaration(2)
						);
			case Question.Q6:
				UserTester user = UserTester.getInstance();
				BookTester book = BookTester.getInstance();
				BorrowingRecordTester record = BorrowingRecordTester.getInstance();
				LibraryManagementTester management = LibraryManagementTester.getInstance();

				return Arrays.asList(
						// existence
						user.checkExistence(5)
						, book.checkExistence(5)
						, record.checkExistence(5)
						, management.checkExistence(5)
						
						// field
						, user.checkFields(5)
						, book.checkFields(2)
						, record.checkFields(2)
						, management.checkFields(3)
						
						// getter
						, user.checkGetterDeclaration(2)

						// setter
						, user.checkSetterDeclaration(2)
						);
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println("Class Not Found: %s".formatted(e.getMessage()));
			for (StackTraceElement st : e.getStackTrace()) {
				System.out.println(st);
			}
			
			return List.of();
		}
	}
}