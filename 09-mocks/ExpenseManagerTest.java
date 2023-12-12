package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    private ExpenseManager expenseManager;
    private FancyService mockService;
    private ExpenseRepository mockRepository;
    @BeforeEach
    public void setUp() {
        mockService = mock(FancyService.class);
        mockRepository = mock(ExpenseRepository.class);
        expenseManager = new ExpenseManager(mockRepository, mockService);
    }
    @Test
    void testCalculateTotal1() {
        Expense expense = new Expense();
        expense.setAmount(1);

        List<Expense> expenses = Collections.nCopies(3, expense);
        when(mockRepository.getExpenses()).thenReturn(expenses);

        assert expenseManager.calculateTotal() == 3;
    }

    @Test
    void testCalculateTotal2() {
        Expense expense = new Expense();
        expense.setAmount(2);
        expense.setCategory("Home");

        List<Expense> homeExpenses = Collections.nCopies(3, expense);

        expense = new Expense();
        expense.setAmount(1);
        expense.setCategory("Car");

        List<Expense> carExpenses = Collections.nCopies(5, expense);

        when(mockRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mockRepository.getExpensesByCategory("Home")).thenReturn(homeExpenses);
        when(mockRepository.getExpensesByCategory("Car")).thenReturn(carExpenses);

        assert expenseManager.calculateTotalForCategory("Home") == 6 : "Home expenses total";
        assert expenseManager.calculateTotalForCategory("Car") == 5 : "Car expenses total";
        assert expenseManager.calculateTotalForCategory("Sport") == 0 : "Sport expenses total";
        assert expenseManager.calculateTotalForCategory("Food") == 0 : "Food expenses total";

    }
    @Test
    void testCalculateTotalInDollars1()
    {
        expenseManager = new ExpenseManager(mockRepository, new FancyService());
        assert expenseManager.calculateTotalInDollars() == 0;
    }
    @Test
    void testCalculateTotalInDollars2() throws ConnectException
    {
        when(mockService.convert(eq(0.0), eq("PLN"), eq("USD"))).thenReturn(0.0);
        when(mockService.convert(eq(12.0), eq("PLN"), eq("USD"))).thenReturn(3.0);

        assert expenseManager.calculateTotalInDollars() == 0;

        Expense expense = new Expense();
        expense.setAmount(3);
        List<Expense> expenses = Collections.nCopies(4, expense);
        when(mockRepository.getExpenses()).thenReturn(expenses);

        assert expenseManager.calculateTotalInDollars() == 3;
    }
    @Test
    void testCalculateTotalInDollars3() throws ConnectException
    {
        when(mockService.convert(anyDouble(), anyString(), anyString())).thenThrow(new ConnectException());

        assert expenseManager.calculateTotalInDollars() == -1;
    }

    @Test
    void testCalculateTotalInDollars4() throws ConnectException
    {
        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        double amount = invocation.getArgument(0);
                        return amount/4;
                    }
                });

        assert expenseManager.calculateTotalInDollars() == 0;

        Expense expense = new Expense();
        expense.setAmount(3);
        List<Expense> expenses = Collections.nCopies(4, expense);
        when(mockRepository.getExpenses()).thenReturn(expenses);

        assert expenseManager.calculateTotalInDollars() == 3;
    }
}
