package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class Manager {
    private Repository repository = new Repository();

    public void save(Issues item) {
        repository.save(item);
    }

    public List<Issues> returnAll() {
        return repository.returnAll();
    }

    public List<Issues> listOpenClosed(Boolean index) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : repository.returnAll())
            if (index == issues.getOpenClosed()) {
                result.add(issues);
            }
        return result;
    }

    public List<Issues> filterByAuthor(String author) {
        List<Issues> resuit = new ArrayList<>();
        for (Issues issues : returnAll()) {
            if (author.equals(issues.Issues())) {
                resuit.add(issues);
            }
        }
        return resuit;
    }

    public List<Issues> filterByAuthorWithPredicate(Predicate<String> byAuthor) {
        List<Issues> resuit = new ArrayList<>();
        for (Issues issues : returnAll()) {
            if (byAuthor.test(issues.getAuthor())) {
                resuit.add(issues);
            }
        }
        return resuit;
    }

    public List<Issues> filterByLabelWithPredicate(Predicate<String> predicateLabel) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : returnAll()) {
            HashSet<String> labels = issues.getLable();
            for (String label : labels) {
                if (predicateLabel.test(label)) {
                    result.add(issues);
                }
            }
        }
        return result;
    }

    public List<Issues> filterByAssigneeWithPredicate(Predicate<String> prAssignee) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : returnAll()) {
            HashSet<String> assignees = issues.getAssignee();
            for (String assignee : assignees)
                if (prAssignee.test(assignee)) {
                    result.add(issues);
                }
        }
        return result;
    }

    public List<Issues> filterByAssignee(String assignee) {
        List<Issues> result = new ArrayList<>();
        for (Issues issues : returnAll()) {
            Set<String> set = issues.getAssignee();
            if (set.contains(assignee)) result.add(issues);
        }
        return result;
    }

    Comparator<Issues> comparatorId = new Comparator<Issues>() {
        @Override
        public int compare(Issues o1, Issues o2) {
            return o1.getId() - o2.getId();
        }
    };

    public List<Issues> sortById() {
        List<Issues> issueList = new ArrayList<>(repository.returnAll());
        Collections.sort(issueList, comparatorId);
        return issueList;
    }

    Comparator<Issues> comparatorAuthor = new Comparator<Issues>() {
        @Override
        public int compare(Issues o1, Issues o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };

    public List<Issues> sortByAuthor() {
        List<Issues> issueList = new ArrayList<>(repository.returnAll());
        Collections.sort(issueList, comparatorAuthor);
        return issueList;
    }

    public Issues openClosedById(int id) {
        Issues result = repository.findById(id);
        boolean openClosed = result.getOpenClosed();
        result.setOpenClosed(!openClosed);
        return result;
    }
}

