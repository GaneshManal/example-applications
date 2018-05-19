# Example Streaming Application: (Flink Streaming API) car-top-speed windowing illustrating Flink windows, triggers and event processing.

The application serves as an example to illustrate the usage of below features in the flink application -
* [Flink Java API](https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/java8.html)
* [Flink Windows](https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/stream/operators/windows.html)
* [Flink Triggers](https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/stream/operators/windows.html#triggers)
* [Flink Connectors - AVRO Support](https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/batch/connectors.html)


## Overview

The example stream processing application shows an example of an application that can be deployed using the PNDA Deployment Manager. (See the `platform-deployment-manager` project for details.)

This example uses the Flink APIs. When PNDA is configured to use HDP/CDH cluster, Flink is deployed by default.

The application is a tar file containing binaries and configuration files required to perform some batch and stream processing.

Application consists of flink configuration arguments and application configuration argument. Application configuration includes zookeeper server ( --bootstrap-server ), kafka server ( --broker-list ) and input topic ( --topic ) specifications.

This example application reads data from the specified kafka topic, deserilizes the data in AVRO format and performs basic counting analytics. In case, the input parameters namely bootstrap-server(zookeeper), broker-list(kafka) and kafka topic name is not provided it uses the default corresponding values as localhost:2181, localhost:9092 and 'test'. 

The results are printed into the console output of the flink driver process by default. To view these, navigate to the log file via the Yarn Resource Manager UI or use the PNDA log server. If required, output can be redirected to the user specific file using --output option.
Also results can be written to the output file by specifying the --output parameter.


# Flink Windows

Windows are at the heart of processing infinite streams. Windows split the stream into “buckets” of finite size, over which we can apply computations.
In example application, A source fetches events from cars every 100 millisecond containing their id, their current speed (km/h), overall elapsed distance (m) and a time stamp. 
The streaming example triggers the top speed of each car every x(200) meters elapsed for the last y(20) seconds.

Example - x = 200 meters , y = 30 seconds.
Here, Flink will trigger every time the car travels 200 meters and with that trigger it will present the information what was the top speed in last time-interval ( window of 30 seconds ). 

Along with total word count, specific word count is performed. This is meant to count the occurrence of specific word during the application execution and present it to the counter metrics ( using Flink custom metrics ) to monitor the application execution.
Therefore, the monitored data for specific word count is reported to the Graphite and same is available to the Grafana for application monitoring. Below is the sample chart -

![counter metrics data-points](images/counter-metrics.JPG)

The above graph is plotted by executing the flink application with 2 containers. The counter values from both the containers during application execution are reported to the Graphite.
The query formation is highlighted in the above image.


# Accumulators

Accumulators are simple constructs with an add operation and a final accumulated result, which is available after the job is complete. To view these, navigate to the log file via the Yarn Resource Manager UI or use the PNDA log server.

# PNDA logging metrics

The metrics can be observed in the metrics tab of the specific application under application management page.

The accumulators are configured in the application to accumulate the specific word count result. The same accumulator count is configured to send to the PNDA logging metrics and shows the instantaneous value at current moment.

Below is the image for the same -

![logging metrics accumulator-count](images/logging-metrics.JPG)

As per the configured reporting interval for Graphite, the logging metric will be reported ( i.e. every one minute ).

## Requirements

* [Maven](https://maven.apache.org/docs/3.0.5/release-notes.html) 3.0.5
* [Java JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) 1.8

## Build
Edit the `batch-processing-app/pom.xml` file with the correct dependencies.

To build the example applications use:

````
mvn clean package
````

This command should be run at the root of the repository and will build the application package. It will create a package file `flink-streaming-car-topspeed-windowing-java-example--app-{version}.tar.gz` in the `app-package/target` directory.

## Files in the package

- `application.properties`: config file used by the Flink Streaming java application.
- `properties.json`: contains default properties that may be overridden at application creation time.

## Deploying the package and creating an application

The PNDA console can be used to deploy the application package to a cluster and then to create an application instance. The console is available on port 80 on the edge node.

To make the package available for deployment it must be uploaded to a package repository. The default implementation is an OpenStack Swift container. The package may be uploaded via the PNDA repository manager which abstracts the container used, or by manually uploading the package to the container.

Once the application is running, the counter metric ( named sample-counter ) can be found in graphite and hence to the Grafana. Also, accumulator results can be observed in the logging metric while application is running and console output of the flink driver process after application ends.
