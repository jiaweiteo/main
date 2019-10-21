package seedu.algobase.model.task;

import static seedu.algobase.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

import seedu.algobase.model.problem.Author;
import seedu.algobase.model.problem.Description;
import seedu.algobase.model.problem.Difficulty;
import seedu.algobase.model.problem.Name;
import seedu.algobase.model.problem.Problem;
import seedu.algobase.model.problem.Remark;
import seedu.algobase.model.problem.Source;
import seedu.algobase.model.problem.WebLink;
import seedu.algobase.model.tag.Tag;

/**
 * Represents a Task in the algobase.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    public static final String DATE_TIME_CONSTRAINTS = "DateTime format should be 'yyyy-MM-dd HH:mm:ss'.";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final long id;
    private final Problem problem;
    private final Boolean isSolved;
    private final LocalDateTime dateTime;

    /**
     * Problem field must be present and not null.
     */
    public Task(Problem problem) {
        requireAllNonNull(problem);
        this.id = System.currentTimeMillis() / 1000L;
        this.problem = problem;
        this.isSolved = false;
        this.dateTime = LocalDateTime.now();
    }

    public Task(Problem problem, boolean isSolved) {
        requireAllNonNull(problem);
        this.id = System.currentTimeMillis() / 1000L;
        this.problem = problem;
        this.isSolved = isSolved;
        this.dateTime = LocalDateTime.now();
    }

    public Task(long id, Problem problem, boolean isSolved) {
        requireAllNonNull(id, problem);
        this.id = id;
        this.problem = problem;
        this.isSolved = isSolved;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Creates and returns a {@code Plan} with the details of {@code planToUpdate}
     * with an updated {@code taskSet}.
     */
    public static Task updateStatus(Task taskToUpdate, boolean isSolved) {
        assert taskToUpdate != null;

        long id = taskToUpdate.id;
        Problem problem = taskToUpdate.problem;

        return new Task(id, problem, isSolved);
    }

    public long getId() {
        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public Name getName() {
        return problem.getName();
    }

    public Author getAuthor() {
        return problem.getAuthor();
    }

    public Description getDescription() {
        return problem.getDescription();
    }

    public WebLink getWebLink() {
        return problem.getWebLink();
    }

    public Difficulty getDifficulty() {
        return problem.getDifficulty();
    }

    public Remark getRemark() {
        return problem.getRemark();
    }

    public Source getSource() {
        return problem.getSource();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return problem.getTags();
    }

    public Boolean getIsSolved() {
        return isSolved;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns status icon of the task.
     *
     * @return the tick icon [✓] if task is done, or cross icon [✗] otherwise.
     */
    public String getStatusIcon() {
        //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
        return this.isSolved ? "[\u2713]" : "[\u2718]";
        //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
    }

    /**
     * Returns true if both tasks have the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getProblem().equals(getProblem())
                && otherTask.getIsSolved().equals(getIsSolved())
                && otherTask.getDateTime().equals(getDateTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(problem, isSolved, dateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Problem: ")
            .append(getProblem())
            .append(" Date: ")
            .append(getDateTime())
            .append(" isSolved: ")
            .append(getIsSolved());
        return builder.toString();
    }

}
