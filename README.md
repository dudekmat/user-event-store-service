# User Event Store Service

## Description
Kafka Consumer reading messages with user events from topics and storing them in Elasticsearch

## Run locally
Use docker compose file with command `docker compose up -d`

Execute following command to run Elasticsearch instance:
`docker run -d --name elasticsearch -p 9200:9200 -e "discovery.type=single-node" elasticsearch:8.3.3`

Create indexes:
`PUT http://localhost:9200/product-event`
`PUT http://localhost:9200/search-event`