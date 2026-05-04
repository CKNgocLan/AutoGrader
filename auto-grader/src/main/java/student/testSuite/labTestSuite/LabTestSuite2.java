package student.testSuite.labTestSuite;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import student.constant.FieldName;
import student.constant.Question;
import student.model.ALabTestSuite;
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
import student.testSuite.lab2.problem5.CarRentalCustomerTester;
import student.testSuite.lab2.problem5.CarRentalTester;
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
			int defaultPoints = 5;

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
				
				List<String> ingredient = Arrays.asList("Egg", "Flour");
				double laborCharge = 2.5;
				double deliveryFee = 1.555;
				Object customerInstance = CustomerTester.initObject("Lao Hac");
				Object eventInstance = EventTester.initObject(0);
				int tierNumber = 2;
				double price = 2.567;
				Object cakeInstance = CakeTester.initObject(customerInstance, eventInstance, tierNumber, price);
				double priceAfterTax = 4.3794;

				return Arrays.asList(
						// customer
						customer.checkExistence(5)
						, customer.checkFields(5)
						, customer.checkNoArgsConstructors(5)
						, customer.checkGetterDeclaration(2)
						, customer.checkSetterDeclaration(2)
						, customer.checkToStringOperation(5)
						
						// const
						, consts.checkDeclaration(5)
						, consts.checkFields(5)
						
						// event
						, event.checkDeclaration(5)
						, event.checkFields(5)

						// cake
						, cake.checkExistence(5)
						, cake.checkPartialArgsConstructors(5,
								new ParameterTesting(CustomerTester.getCorrespondingClass()),
								new ParameterTesting(EventTester.getCorrespondingClass()),
								new ParameterTesting(int.class),
								new ParameterTesting(double.class)
						)
						, cake.checkFields(5)
						, cake.checkGetterDeclaration(5)
						, cake.checkSetterDeclaration(5)
						, cake.checkToStringDeclaration(5)
						
						// quote
						, quote.checkExistence(5)
						, quote.checkFields(5)
						, quote.checkPartialArgsConstructorDeclaration(5,
								new ParameterTesting(CakeTester.getCorrespondingClass()),
								new ParameterTesting(double.class),
								new ParameterTesting(double.class)
						)
						, quote.checkGetterDeclaration(5)
						, quote.checkSetterDeclaration(5)
						, quote.checkToStringDeclaration(5)
						, quote.checkToStringOperation(5, ingredient, laborCharge, deliveryFee, cakeInstance)
						, quote.checkGetPriceAfterTaxDeclaration(5)
						, quote.checkGetPriceAfterTaxOperation(5, laborCharge, deliveryFee, priceAfterTax)
				);
			case Question.Q5:
				consts = ConstTester.getInstance();
				CarRentalCustomerTester rentalCustomer = CarRentalCustomerTester.getInstance();
				AgreementTester agreement = AgreementTester.getInstance();
				CarRentalTester rentalCar = CarRentalTester.getInstance();
				
				String name = "Lao Hac";
				String address = "Vu Dai village";
				String license = "a3122232";
				
				String make = "Mazda";
				String model = "MX-5";
				int period = 5;
				int mileageLimit = 90;
				Object customerObj = CarRentalCustomerTester.initObject(name);
				Object carObj = CarRentalTester.initObject(make, model, period, mileageLimit, customerObj);
				
				String purpose = "travel";
				double baseRentalFee = 50;
				double mileageFee = 15;
				double rentalCostAfterTax = 70.2;

				return Arrays.asList(
						// customer
						rentalCustomer.checkExistence(5)
						, rentalCustomer.checkFields(2)
						, rentalCustomer.checkGetterDeclaration(2)
						, rentalCustomer.checkSetterDeclaration(2)
						, rentalCustomer.checkPartialArgsConstructors(5, new ParameterTesting(String.class))
						, rentalCustomer.checkToStringOperation(5, name, address, license)

						// const
						, consts.checkDeclaration(5)
						, consts.checkFields(5)
						
						// rental car
						, rentalCar.checkExistence(5)
						, rentalCar.checkFields(5)
						, rentalCar.checkPartialArgsConstructorDeclaration(5
								, new ParameterTesting(String.class)
								, new ParameterTesting(String.class)
								, new ParameterTesting(int.class)
								, new ParameterTesting(int.class)
								, new ParameterTesting(CarRentalCustomerTester.getCorrespondingClass())
						)
						
						// agreement
						, agreement.checkExistence(5)
						, agreement.checkFields(5)
						, agreement.checkGetRentalCostAfterTaxOperation(mileageLimit, carObj, purpose, baseRentalFee, mileageFee, rentalCostAfterTax)
						);
			case Question.Q6:
				UserTester user = UserTester.getInstance();
				BookTester book = BookTester.getInstance();
				BorrowingRecordTester record = BorrowingRecordTester.getInstance();
				LibraryManagementTester management = LibraryManagementTester.getInstance();

				return Arrays.asList(
						// user
						user.checkExistence(defaultPoints)
						, user.checkFields(defaultPoints)
						, user.checkPartialArgsConstructorDeclaration(defaultPoints
								, new ParameterTesting(String.class)
								, new ParameterTesting(String.class)
						)
						, user.checkGetterDeclaration(defaultPoints)
						, user.checkSetterDeclaration(defaultPoints)
						, user.checkEqualsDeclaration(defaultPoints)
						
						// book
						, book.checkExistence(5)
						, book.checkFields(2)
						, book.checkFullArgsConstructorDeclaration(defaultPoints
								, new ParameterTesting(String.class)
								, new ParameterTesting(String.class)
						)
						, book.checkGetterDeclaration(defaultPoints)
						, book.checkSetterDeclaration(defaultPoints)
						, book.checkEqualsDeclaration(defaultPoints)
						
						// borrowing record
						, record.checkExistence(5)
						, record.checkFields(2)
						, record.checkPartialArgsConstructorDeclaration(defaultPoints
								, new ParameterTesting(UserTester.getCorrespondingClass())
								, new ParameterTesting(BookTester.getCorrespondingClass())
								, new ParameterTesting(LocalDate.class)
						)
						, record.checkGetterDeclaration(defaultPoints)
						, record.checkSetterDeclaration(defaultPoints)
						, record.checkEqualsDeclaration(defaultPoints)
						
						// library management
						, management.checkExistence(5)
						, management.checkFields(3)
						, management.addUserDeclaration(defaultPoints)
						, management.addBookDeclaration(defaultPoints)
						, management.addBorrowingRecordDeclaration(defaultPoints)
						, management.getBorrowingBooksDeclaration(defaultPoints)
						, management.isValidUserDeclaration(defaultPoints)
						, management.isUserEligibleToBorrowDeclaration(defaultPoints)
						, management.showAllUsersDeclaration(defaultPoints)
						, management.showAllBooksDeclaration(defaultPoints)
						, management.showAllBorrowingRecordsDeclaration(defaultPoints)
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