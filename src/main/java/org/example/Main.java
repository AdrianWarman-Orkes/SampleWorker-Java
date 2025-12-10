package org.example;

import com.netflix.conductor.sdk.workflow.executor.WorkflowExecutor;
import io.orkes.conductor.client.ApiClient;

public class Main {

    // set credentials to authenticate connection to Conductor server
    private static final String CONDUCTOR_SERVER = "<insert_url>";
    private static final String KEY = "<insert_key>";
    private static final String SECRET = "<insert_secret>";

    public static void main(String[] args) {

        // Create an authenticated API client for communicating with Conductor
        // The ApiClient handles:
        // - Authentication using the key/secret
        // - Base URL configuration
        // - Request signing
        var apiClient = new ApiClient(CONDUCTOR_SERVER, KEY, SECRET);

        // WorkflowExecutor is responsible for:
        // - Registering workers
        // - Polling Conductor for tasks
        // - Executing worker methods
        // - Publishing results back to Conductor
        //
        // The second parameter (500) is the polling interval in milliseconds.
        var workflowExecutor = new WorkflowExecutor(apiClient, 500);

        // Scan the given package ("org.example") for worker classes.
        // Any class annotated with @WorkerTask will be automatically registered.
        //
        // This starts polling Conductor for any tasks assigned to those workers.
        workflowExecutor.initWorkers("org.example");
    }
}