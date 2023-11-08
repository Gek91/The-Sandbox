package testExample.integration;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import testExample.model.Expense;
import testExample.model.ExpenseSplit;
import testExample.model.ExpenseType;
import testExample.respository.ExpenseCrudRepository;
import testExample.service.ExpenseService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TestContainersTest {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0-debian");

	@BeforeAll
	static void beforeAll() {
		mysql.start();
	}

	@AfterAll
	static void afterAll() {
		mysql.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
//		registry.add("spring.datasource.driverClassName", mysql::getDriverClassName);
	}

	@Autowired
	private ExpenseCrudRepository expenseRepository;
	@Autowired
	private ExpenseService expenseService;

	@Test
	public void testGetExpenseAmount() {

		Long count = expenseRepository.count();
		Assertions.assertEquals(count, Long.valueOf(1l));

		Optional<Expense> expenseOptional = expenseRepository.findById(1l);

		Assertions.assertTrue(expenseOptional.isPresent());

		BigDecimal result = expenseService.getExpenseAmount(expenseOptional.get());

		Assertions.assertNotNull(result);
		Assertions.assertEquals(BigDecimal.valueOf(50l).setScale(2), result);
	}

	@Test
	public void testAddExpense() {

		Long count = expenseRepository.count();

		Expense expense = new Expense();
		expense.setType(new ExpenseType(1l, ""));
		expense.setCreationDatetime(Instant.now());
		expense.setModificationDatetime(expense.getCreationDatetime());
		expense.setExpenseDate(LocalDate.now());
		List<ExpenseSplit> expenseSplitList = new ArrayList<>();
		expense.setSplits(expenseSplitList);

		ExpenseSplit split1 = new ExpenseSplit();
		split1.setAmount(BigDecimal.valueOf(10l));
		split1.setRecordDate(LocalDate.now().plusDays(1));
		expenseSplitList.add(split1);

		ExpenseSplit split2 = new ExpenseSplit();
		split2.setAmount(BigDecimal.valueOf(30l));
		split2.setRecordDate(LocalDate.now().minusDays(1));
		expenseSplitList.add(split2);

		expenseRepository.save(expense);

		Assertions.assertEquals(2, expenseRepository.count());
	}
}
