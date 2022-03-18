Runnable
- Interface to define a Task that executes concurrently.

Thread
- Executes multiple tasks concurrently.
- Executes Runnable.
- has priorities, state.
- can sleep(postpone execution till further notice :D ).
- can synchronize keyword. (method or block) (only one thread can execute at a time)
- can Message (wait, notify).

Callable 
- represents a thread that returns values
- usage examples:
    - numerical type computations in which partial results are computed simultaneously
    - return a status code that indicates the Success/Failure/Partial-Success state.

Future
- it is a generic interface that represents the value that will be returned by a Callable object. since Callable will return result in future it's called Future.

Executor
- offers an alternative to managing threads through the Thread class.
- executes Runnable.

ExecutorService 
- extends Executor.
- execute threads that return results.

ScheduledExecutorService
- extends ExecutorService.
- supports the scheduling of thread.

Executors
- utility class which provides implemantations(static factory methods) of Executor, ExecutorService, ScheduledExecutorService

Delayed
- interface which provides building block to make ScheduledFuture
- provides a method which returns time left to spawn the Task.

ScheduledFuture
- extends Delayed and Future.
- easily cancel planned tasks if required.