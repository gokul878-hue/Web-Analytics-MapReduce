#!/bin/bash

HADOOP=/workspaces/hadoop-3.4.3/bin/hadoop
HDFS=/workspaces/hadoop-3.4.3/bin/hdfs
OUTPUT=/web_analytics/output/part-r-00000

echo "=================================="
echo "       WEB ANALYTICS RESULTS"
echo "=================================="

echo ""
echo "All IP Addresses and Request Counts:"
$HDFS dfs -cat "$OUTPUT"

echo ""
echo "Most Active IP Address:"
$HDFS dfs -cat "$OUTPUT" | sort -k2,2nr | head -n 1

echo ""
echo "=================================="
