package org.example;

import com.netflix.conductor.common.metadata.tasks.TaskResult;
import com.netflix.conductor.sdk.workflow.task.InputParam;
import com.netflix.conductor.sdk.workflow.task.WorkerTask;

public class Workers {

    // @WorkerTask registers this method as a Conductor worker.
    // value = "hello_world" → The taskType this worker listens for.
    // threadCount = 5 → Number of parallel worker threads polling for tasks.
    // pollingInterval = 100 → Overrides pollingInterval set in Main class
    @WorkerTask(value="hello_world", threadCount = 5, pollingInterval = 100)
    public TaskResult helloWorld(@InputParam("name") String name) {

        // TaskResult holds the output and final status of the worker execution.
        TaskResult result = new TaskResult();

        // Add output data that will be returned to the workflow.
        // "message" will appear as task.output.message in Conductor.
        result.addOutputData("message", "Hello " + name);

        // Mark the task as successfully completed.
        result.setStatus(TaskResult.Status.COMPLETED);

        // Return the result back to Conductor.
        return result;
    }
}