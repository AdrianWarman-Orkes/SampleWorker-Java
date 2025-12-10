# Java Conductor Worker Example

A simple Java worker example for Orkes Conductor. It demonstrates how to connect to a Conductor server, authenticate using an Orkes Application Key and Secret, register workers, poll for tasks, and return results. This project serves as a template for developers building Java-based workers for Conductor.

## Requirements and Setup

Before running this worker, you must:

- Install Conductor Java SDK

For Gradle:
Add the following implementations to your build.gradle:

implementation 'org.conductoross:conductor-client:4.0.3'
implementation 'org.conductoross:java-sdk:4.0.3'
implementation 'io.orkes.conductor:orkes-conductor-client:4.0.3'
implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.2'


For Maven:
Add the following to pom.xml inside <dependencies></dependencies>

<dependency>
   <groupId>org.conductoross</groupId>
   <artifactId>conductor-client</artifactId>
   <version>4.0.18</version>
</dependency>
<dependency>
   <groupId>org.conductoross</groupId>
   <artifactId>java-sdk</artifactId>
   <version>4.0.18</version>
</dependency>
<dependency>
   <groupId>io.orkes.conductor</groupId>
   <artifactId>orkes-conductor-client</artifactId>
   <version>4.0.18</version>
</dependency>
<dependency>
   <groupId>com.fasterxml.jackson.core</groupId>
   <artifactId>jackson-databind</artifactId>
   <version>2.17.2</version>
</dependency>


- Have access to an Orkes Conductor instance (Orkes Cloud or self-hosted)
- Create an Application inside the Orkes Console
- Generate an API Key and API Secret
- Assign Worker Permissions for each task type the worker will execute (for example: "hello_world" in this demo)

If your application does not have these permissions, the worker will not be able to poll tasks from Conductor.

## What This Worker Provides

This example registers one worker for the task type "hello_world".

When executed, it reads the workflow input field "name" and returns a message:

Hello <name>

This demonstrates how to read inputs from a Conductor task, produce a TaskResult, and send output back to the workflow.

## Configuration

Before running the example, update the server URL and credentials inside the Main class:

private static final String CONDUCTOR_SERVER = "<insert_url>";
private static final String KEY = "<insert_key>";
private static final String SECRET = "<insert_secret>";

These values correspond to:

- CONDUCTOR_SERVER → Your Orkes Conductor API endpoint
- KEY and SECRET → API credentials your Orkes Application generated
- The application must have worker permissions for the tasks you want to run

The Main class initializes the ApiClient and scans the worker package for WorkerTask annotations.

## Running the Worker

Use:

./gradlew run

or

gradle run

The worker will connect to Conductor, begin polling, and execute any incoming tasks of type "hello_world". It will continue running until the process is stopped.

## Extending This Template

To add more workers:

1. Create additional methods inside the Workers class (or new classes).
2. Annotate each method with @WorkerTask.
3. Define a unique taskType for each worker.
4. Ensure your Orkes Application has permissions for those task types.
5. Rebuild and run the worker.

## Useful Links

- Orkes Documentation: https://orkes.io/content/
- Java Conductor SDK: https://github.com/conductor-sdk/conductor-java
- Orkes Cloud Console: https://console.orkes.io
