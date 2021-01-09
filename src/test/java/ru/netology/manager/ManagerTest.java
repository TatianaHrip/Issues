package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ManagerTest {

    private Manager manager = new Manager();
    private Repository repository = new Repository();

    private Issues issues1 = new Issues (1, "Kim", "Header1", "Text1", true,
            new HashSet<String>(Arrays.asList("label1", "label2")),
            new HashSet<String>(Arrays.asList("assignee2", "assignee1")));

    private Issues issues2 = new Issues (2, "Kim", "Header2", "Text2", false,
            new HashSet<String>(Arrays.asList("label3", "label2")),
            new HashSet<String>(Arrays.asList("assignee1", "assignee4")));

    private Issues issues3 = new Issues(3, "Han", "Header3", "Text3", false,
            new HashSet<String>(Arrays.asList("label1", "label4")),
            new HashSet<String>(Arrays.asList("assignee5", "assignee3")));

    private Issues issues4 = new Issues(4, "BanChan", "Header4", "Text4", true,
            new HashSet<String>(Arrays.asList("label5", "label6")),
            new HashSet<String>(Arrays.asList("assignee3", "assignee2")));

    @BeforeEach
    public void before() {
        manager.save(issues2);
        manager.save(issues4);
        manager.save(issues1);
        manager.save(issues3);
    }

    @Test
    public void saveIssuesNew() {
        Issues issue3_N = new Issues(300, "Kim+++", "Header3+++", "Text3+++", true,
                new HashSet<String>(Arrays.asList("label1", "label2")),
                new HashSet<String>(Arrays.asList("assignee1", "assignee2")));
        manager.save(issue3_N);
        List<Issues> expected = List.of(issues2, issues4, issues1, issues3, issue3_N);
        List<Issues> actual = manager.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void saveIssues() {
        Collection<Issues> expected = List.of(issues1, issues2, issues3, issues4);
        repository.addAll(expected);
        Collection<Issues> actual = repository.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void listOpen() {
        Collection<Issues> expected = List.of(issues4, issues1);
        Collection<Issues> actual = manager.listOpenClosed(true);
        assertEquals(expected, actual);
    }

    @Test
    public void listClosed() {
        List<Issues> expected = List.of(issues2, issues3);
        List<Issues> actual = manager.listOpenClosed(false);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthor() {
        List<Issues> expected = List.of(issues2, issues3);
        List<Issues> actual = manager.filterByAuthor("Kim");
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAuthorWithPredicate() {
        Predicate<String> equalsByAuthor = t -> t.equals("Kim");
        List<Issues> expected = List.of(issues2, issues3);
        List<Issues> actual = manager.filterByAuthorWithPredicate(equalsByAuthor);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByLabelWithPredicate() {
        List<Issues> expected = List.of(issues1, issues3);
        String label = "label1";
        Predicate<String> equalsByLabel = a -> a.equals(label);
        List<Issues> actual = manager.filterByLabelWithPredicate(equalsByLabel);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeWithPredicate() {
        List<Issues> expected = List.of(issues3);
        Predicate<String> equalsDyAssignee = t -> t.equals("assignee5");
        List<Issues> actual = manager.filterByAssigneeWithPredicate(equalsDyAssignee);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeFor2AssWithPredicate() {
        List<Issues> expected = List.of(issues4, issues1);
        Predicate<String> equalsDyAssignee = t -> t.equals("assignee2");
        List<Issues> actual = manager.filterByAssigneeWithPredicate(equalsDyAssignee);
        assertEquals(expected, actual);
    }

    @Test
    public void filterByAssigneeFor2Ass() {
        List<Issues> expected = List.of(issues4, issues1);
        List<Issues> actual = manager.filterByAssignee("assignee2");
        assertEquals(expected, actual);
    }

    @Test
    public void sortById() {
        List<Issues> expected = List.of(issues1, issues2, issues3, issues4);
        List<Issues> actual = manager.sortById();
        assertEquals(expected, actual);
    }

    @Test
    public void sortByAuthor() {
        List<Issues> expected = List.of(issues4, issues2, issues3, issues1);
        List<Issues> actual = manager.sortByAuthor();
        assertEquals(expected, actual);
    }

    @Test
    public void openClosedById() {
        Issues issue_N = new Issues(4, "BanChan", "Header4", "Text4", false,
                new HashSet<String>(Arrays.asList("label5", "label6")),
                new HashSet<String>(Arrays.asList("assignee3", "assignee2")));
        List<Issues> expected = List.of(issue_N);
        List<Issues> actual = Arrays.asList(manager.openClosedById(4));
        assertEquals(expected, actual);
    }
}
