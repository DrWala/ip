import java.util.HashMap;

public class Duke {
    static TaskList tasks;
    static DataStorage storage;
    static boolean shouldRun = true;
    public static void main(String[] args) {

        startup();
        Ui.greet();

        while (shouldRun) {
            try {
                String input = Ui.readCommand();
                HashMap<String, String> parsedCommands = Parser.parseCommand(input);
                switch (parsedCommands.get("command")) {
                case "todo": {
                    tasks.addTodo(parsedCommands);
                    break;
                }
                case "event": {
                    tasks.addEvent(parsedCommands);
                    break;
                }
                case "deadline": {
                    tasks.addDeadline(parsedCommands);
                    break;
                }
                case "list": {
                    tasks.listTasks();
                    break;
                }
                case "done": {
                    tasks.markTaskAsDone(parsedCommands);
                    break;
                }
                case "delete": {
                    tasks.deleteTask(parsedCommands);
                    break;
                }
                case "find": {
                    tasks.findTasks(parsedCommands);
                    break;
                }
                case "bye": {
                    Ui.echo("Bye. Hope to see you again soon!");
                    shouldRun = false;
                    break;
                }
                default: {
                    Ui.echo(String.format("I'm sorry, I don't know what %s means.", input));
                    break;
                }
                }
            } catch (DukeException dukeException) {
                Ui.echo(String.format("Francis encountered an error while processing your request. Here are the details:\n%s", dukeException.getMessage()));
            } catch (Exception e) {
                Ui.echo(String.format("Francis encountered an unexpected while processing your request. Here are the details:\n%s", e.getMessage()));
            }
        }

        shutdown();
    }

    public static void startup() {
        try {
            storage = new DataStorage();
            storage.createBackingStoreIfNotExists();
            tasks = new TaskList(storage.readTasks());
        } catch (DukeException dukeException) {
            Ui.echo(dukeException.getMessage());
            System.exit(0);
        }
    }

    public static void shutdown() {
        tasks.persist(storage);
    }
}
