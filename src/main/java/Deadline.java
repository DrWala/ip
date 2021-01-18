public class Deadline extends Task{
    private String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", done ? "X" : " ", taskName, this.deadline);
    }
}