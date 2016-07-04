#!/bin/bash
# Author: Zak Hassan
# Convenience script for downloading artifacts before running ansible script. You will need to change 'get_url' to 'copy'

curl -o logstash-2.3.2.tar.gz https://download.elastic.co/logstash/logstash/logstash-2.3.2.tar.gz
curl -o elasticsearch-2.3.3.tar.gz   https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/tar/elasticsearch/2.3.3/elasticsearch-2.3.3.tar.gz
curl -o kibana-4.5.1-linux-x64.tar.gz https://download.elastic.co/kibana/kibana/kibana-4.5.1-linux-x64.tar.gz
tar -zxvf elasticsearch-2.3.3.tar.gz
tar -zxvf kibana-4.5.1-linux-x64.tar.gz
tar -zxvf logstash-2.3.2.tar.gz
rm -rf *.tar.gz
