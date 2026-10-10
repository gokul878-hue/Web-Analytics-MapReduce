# Web Analytics Using Hadoop MapReduce

## 1. Project Overview

This project implements a Hadoop MapReduce program to analyze website access logs and identify the most active IP addresses based on their number of requests.

**Course:** Big Data Analytics Laboratory  
**Technology:** Apache Hadoop, HDFS, Java, MapReduce

## 2. Problem Statement

Develop a MapReduce program to process website access logs and calculate the number of requests made by each IP address.

## 3. Objectives

- Store website access logs in HDFS.
- Process CSV records using Hadoop MapReduce.
- Count requests for each IP address.
- Identify the IP address with the highest request count.

## 4. Dataset

The sample dataset is `input/web_access_log.csv`.

| Column | Description |
|---|---|
| timestamp | Date and time of the request |
| ip | Client IP address |
| method | HTTP method |
| url | Requested resource |
| status | HTTP response status |

The sample dataset contains 20 requests from 5 unique IP addresses.

## 5. Technologies Used

- Java 11
- Apache Hadoop 3.4.3
- Hadoop Distributed File System (HDFS)
- Hadoop MapReduce
- Linux terminal / GitHub Codespaces

## 6. Project Structure

```text
Web-Analytics-MapReduce/
├── input/
│   └── web_access_log.csv
├── src/
│   ├── IPRequestMapper.java
│   ├── IPRequestReducer.java
│   └── IPRequestCount.java
├── show_results.sh
├── README.md
└── .gitignore
```

## 7. MapReduce Workflow

1. Store the CSV dataset in HDFS.
2. The Mapper extracts the IP address from each valid row and emits `(IP, 1)`.
3. Hadoop groups intermediate values by IP address.
4. The Reducer sums the values for each IP.
5. The results are stored in HDFS.
6. The result script displays all counts and identifies the most active IP.

## 8. HDFS Paths

Input directory:

`/web_analytics/input`

Output directory:

`/web_analytics/output`

## 9. Build and Execution

Compile the Java source files using Java 11 and Hadoop's classpath:

```bash
mkdir -p build

/usr/lib/jvm/java-11-openjdk-amd64/bin/javac \
  -classpath "$(/workspaces/hadoop-3.4.3/bin/hadoop classpath)" \
  -d build \
  src/IPRequestMapper.java \
  src/IPRequestReducer.java \
  src/IPRequestCount.java
```

Create the JAR:

```bash
jar -cvf IPRequestCount.jar -C build .
```

Run the MapReduce job after starting the required Hadoop services:

```bash
/workspaces/hadoop-3.4.3/bin/hadoop jar IPRequestCount.jar \
  IPRequestCount \
  /web_analytics/input \
  /web_analytics/output
```

Display the output:

```bash
/workspaces/hadoop-3.4.3/bin/hdfs dfs -cat \
  /web_analytics/output/part-r-00000
```

Identify the most active IP:

```bash
./show_results.sh
```

Note: Hadoop requires the output directory not to exist before a new job run. If it already exists, inspect the existing results before removing it.

## 10. Results

The program successfully processed the sample dataset and produced the following counts:

| IP address | Request count |
|---|---:|
| 192.168.1.10 | 7 |
| 192.168.1.11 | 4 |
| 10.0.0.5 | 3 |
| 192.168.1.12 | 3 |
| 172.16.0.7 | 3 |
| **Total** | **20** |

**Most active IP address:** `192.168.1.10`  
**Total requests from this IP:** 7

## 11. Conclusion

This project demonstrates how Hadoop HDFS stores website access logs and how MapReduce processes the records to calculate request counts for individual IP addresses. The implementation successfully identified the most active IP address in the sample dataset.

## 12. Future Enhancements

- Support larger website access log datasets.
- Analyze HTTP status codes and frequently requested URLs.
- Generate visual summaries of traffic.
- Add date-wise and hourly traffic analysis.
