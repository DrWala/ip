import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime timeOfEvent;

    public Event(String taskName, LocalDateTime timeOfEvent) {
        super(taskName);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return String.format("[E][%s] %s (at: %s)", done ? "X" : " ", taskName, this.timeOfEvent.format(formatter));
    }
}
