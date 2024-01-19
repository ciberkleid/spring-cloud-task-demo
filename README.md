# Spring Cloud Task Demo

This repo contains basic functionality of Spring Cloud Task.

It also contains two ways to start a Postgres database for Spring Cloud Task, Docker Compose and Testcontainers for Development Time.

## Usage Instructions

Steps:
1. Run the application
2. View the results of in the database

### Step 1. Run the application

You can use either option below.
If you are curious about using Docker Compose vs Testcontainers to start backend containers during development, you can take a closer look at the options.
Otherwise, if your focus is to learn about Spring Cloud Task, choose one at random and move on to step 2.

#### Option 1: Using Docker Compose to start the required database container

To use Docker Compose to start Postgres:
```shell
./mvnw spring-boot:run
```

Notice the container is configured to remain running after the Task has completed.
(See [application.properties in src/main](src/main/resources/application.properties)). 
```shell
# In src/main/resources/application.properties
spring.docker.compose.lifecycle-management=start_only
```

After the Task has completed, the application will shut down.
You can connect to the database from the command line using the `psql` CLI as follows.
Notice that the configuration details match those configured in [docker-compose.yml](docker-compose.yml).
```shell
PGPASSWORD=postgres psql -h localhost -p 5432 -d postgres -U postgres -w
```

#### Option 2: Using Testcontainers for Dev Time to start the required database container

To use Testcontainers for Dev Time to start Postgres:
```shell
./mvnw spring-boot:test-run
```

Notice the container is configured to remain running after the Task has completed.
(See [application.properties in src/test](src/test/resources/application.properties)).
```shell
# In src/test/resources/application.properties
spring.docker.compose.lifecycle-management=none
my.testcontainers.reuse.enabled=true
```

After the Task has completed, the application will shut down.
You can connect to the database from the command line using the `psql` CLI as follows.
> Note: In this case, the command uses the default values ("test") provided by Testcontainers for all parameters except for the port.
> You need to examine the application log file and obtain the port, which is dynamically mapped to an unused port on your local machine.
> Look for the JDBC URL printed to the log file and use that port in te command below.
The port is match those configured in [docker-compose.yml](docker-compose.yml).
```shell
PGPASSWORD=test psql -h localhost -p 57296 -d test -U test -w
```

### Step 2. View the results of in the database

Inside the `psql` shell, type `\z` to see the list of tables.

```shell
psql (16.1)
Type "help" for help.

test=# \z
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

You can further explore the contents with SQL queries.
