# Read Me First

* snnLab project that is related Spring Batch Job(Chunk oriented File to File) on Local Spring Cloud Data Flow platform.
* Local OS:Ubuntu 20.04.1 LTS , Project Management Tool: Maven , Language Platform: JDK_11  
* Spring Cloud Data Flow consists of services namely, PostgreSQL, Kafka, Zookeeper, Dataflow Server, Skipper Server, and some ready data flow app registry.
* Application registery is choiced as mounting Local Maven Repositor. Also Docker Image or other repository can be tried.
* Local SCDF is not supported Scheduler Job, also Kubernates or other cloud platforms can be used.
* Local terminal is integrated embedded H2 database.

# Deployment and Running on Spring Cloud Data Flow

* Before installation Spring Cloud Data Flow(SCDF), change information on docker-compose.yml (HOST_MOUNT_PATH, DOCKER_MOUNT_PATH, and all volumes.) according to your local settings.
* To install Spring Cloud Data Flow on your local machine , run 'docker-compose up' command on installation directory.
* Dashboard is opened on 'http://localhost:9393/dashboard/#/apps'
* Register batch app  on 'Add Application Menu' -> Register One or More Application
* Select type 'task'
* Url as 'maven://com.snnlab:spring-cloud-data-flow-batch:0.0.1-SNAPSHOT'  
* Create task on Tasks - 'Create Task'
* Select registerd app, drag right panel , and connect between start and end.
* Copy Input and Output files on project directory into Local Mounting directory with SCDF.(Normally File is sourced on SFTP,FTP, or other platform, but locally is enough.)    
* Launch app on Tasks menu
* View Task Execution and Job Execution info
* Besides above task deployment actions, you can import file 'tasks-export_2021-01-133506.json' on /installation directory into SCDF.

# Running on Local Terminal

* Change commented properties application-local.yaml 
* Run Spring Boot Main class with environment variable 'ENV=local'

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#howto-batch-applications)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Spring_Cloud_Data_Flow](https://docs.spring.io/spring-cloud-dataflow/docs/current/reference/htmlsingle/#getting-started)





