package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {
    private ExpenseRepository repository;
    @Test
    void testLoadExpenses1() {
        repository = new ExpenseRepository(new FancyDatabase());
        repository.loadExpenses();
        assert repository.getExpenses().isEmpty() : "Returned list should be empty";
    }

    @Test
    void testLoadExpenses2() {
        repository = new ExpenseRepository(new MyDatabase());
        repository.loadExpenses();
        assert repository.getExpenses().isEmpty() : "Returned list should be empty";
    }

    @Test
    void testLoadExpenses3() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());
        InOrder order = inOrder(mockDatabase);

        repository = new ExpenseRepository(mockDatabase);

        repository.loadExpenses();

        assert repository.getExpenses().isEmpty() : "Returned list should be empty";
        order.verify(mockDatabase).connect();
        order.verify(mockDatabase).queryAll();
        order.verify(mockDatabase).close();
    }
    @Test
    void testSaveExpenses1() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        Expense expense = new Expense();

        repository = new ExpenseRepository(mockDatabase);

        repository.addExpense(expense);
        repository.saveExpenses();

        verify(mockDatabase).persist(expense);
    }
    @Test
    void testSaveExpenses2() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());
        Expense expense = new Expense();

        repository = new ExpenseRepository(mockDatabase);

        repository.addExpense(expense);
        repository.saveExpenses();

        verify(mockDatabase).persist(any(Expense.class));
    }
    @Test
    void testSaveExpenses3() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());

        repository = new ExpenseRepository(mockDatabase);
        for(int i = 0; i < 5; i++)
        {
            Expense expense = new Expense();
            repository.addExpense(expense);
        }
        repository.saveExpenses();

        verify(mockDatabase, times(5)).persist(any(Expense.class));
    }
}
