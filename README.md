# Spring Cloud Task Demo

This repo contains basic functionality of Spring Cloud Task.

## Usage Instructions

Steps:
1. Run the application
2. View the results of in the database

### Step 1. Run the application

The application uses Spring Boot's support for Docker Compose to automatically start a Postgres database on the local Docker daemon.

Run the application.
It will complete the task and shut itself down.
```shell
./mvnw spring-boot:run
```

Notice the container is configured to remain running after the Task has completed.
(See [application.properties](src/main/resources/application.properties)). 
```properties
# In src/main/resources/application.properties
spring.docker.compose.lifecycle-management=start_only
```

After the Task has completed and the application has shut down, connect to the database and check the results.
You can use your IDE (see connection details in [docker-compose.yml](docker-compose.yml)), or you can connect from the command line using the `psql` CLI as follows.
```shell
PGPASSWORD=postgres psql -h localhost -p 5432 -d postgres -U postgres -w
```
### Step 2. View the results of in the database

Inside the `psql` shell, type `\z` to see the list of tables.

```sql
psql (16.1)
Type "help" for help.

postgres=# \z
                                      Access privileges
 Schema |         Name          |   Type   | Access privileges | Column privileges | Policies 
--------+-----------------------+----------+-------------------+-------------------+----------
 public | task_execution        | table    |                   |                   | 
 public | task_execution_params | table    |                   |                   | 
 public | task_lock             | table    |                   |                   | 
 public | task_seq              | sequence |                   |                   | 
 public | task_task_batch       | table    |                   |                   | 
(5 rows)
```

Check the list of executions using the `select` statement shown below.
This list of task executions should grow by one every time you run the app.
```sql
postgres=# select * from task_execution;
task_execution_id |         start_time         |         end_time          |       task_name        | exit_code | exit_message | error_message |        last_updated        | external_execution_id | parent_execution_id 
-------------------+----------------------------+---------------------------+------------------------+-----------+--------------+---------------+----------------------------+-----------------------+---------------------
                 1 | 2024-01-22 13:16:13.166464 | 2024-01-22 13:16:13.19172 | spring-cloud-task-demo |         0 |              |               | 2024-01-22 13:16:13.210635 |                       |                    
(1 row)
```

Type `quit` to exit the psql shell.