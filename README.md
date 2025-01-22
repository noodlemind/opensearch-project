# OpenSearch Spring Boot Starter Project

This project is a **starter template** designed to help you quickly get started with integrating **Spring Boot** and **OpenSearch**. It includes:

- **REST APIs** for querying and managing OpenSearch indices.
- A pre-configured **Docker Compose** setup to spin up local instances of OpenSearch and OpenSearch Dashboards.

---

## Prerequisites

Before starting the project, ensure you have:

- **Java 17** or later
- **Maven**
- **Docker** & **Docker Compose**
- **OpenSearch Sample Data** (ecommerce dataset)

---

## Setup & Running

Follow these steps to set up and run the project:

1. **Start OpenSearch and Dashboards**
   ```bash
   docker compose up -d
   ```
   Default credentials for OpenSearch Dashboards:
   - Username: `admin`
   - Password: `admin`

2. **Build and Run the Spring Boot Application**
   ```bash
   ./mvnw clean package
   java -jar target/opensearch-service-1.0-SNAPSHOT.jar
   ```

---

## REST API Endpoints

### 1. Search Data in an Index
Endpoint:
```http
GET /api/search/{indexName}?query={searchQuery}
```

#### Parameters:
- **`indexName`**: Name of the OpenSearch index (for example, `kibana_sample_data_ecommerce`)
- **`query`**: OpenSearch query string

#### Example Request:
```bash
curl -X GET "http://localhost:8080/api/search/kibana_sample_data_ecommerce?query=customer_gender:MALE" \
     -H "Content-Type: application/json"
```

#### Example Response:
```json
[
  {
    "customer_id": "12",
    "customer_gender": "MALE",
    "customer_full_name": "John Doe",
    "taxful_total_price": 329.99,
    "order_date": "2024-01-22T10:00:00Z",
    "products": [
      {
        "product_name": "Laptop",
        "price": 299.99,
        "category": "electronics",
        "quantity": 1
      }
    ]
  }
]
```

---

### 2. Get Index Mapping
Endpoint:
```http
GET /api/search/{indexName}/mapping
```

#### Parameters:
- **`indexName`**: Name of the OpenSearch index

#### Example Request:
```bash
curl -X GET "http://localhost:8080/api/search/kibana_sample_data_ecommerce/mapping"
```

#### Example Response:
```json
{
  "properties": {
    "category": {
      "fields": {
        "keyword": {
          "type": "keyword"
        }
      },
      "type": "text"
    },
    "customer_birth_date": {
      "type": "date"
    },
    "customer_gender": {
      "type": "keyword"
    }
  }
}
```

---

## Sample Queries

### 1. Search by Customer Gender
```bash
curl -X GET "http://localhost:8080/api/search/kibana_sample_data_ecommerce?query=customer_gender:FEMALE"
```

### 2. Search by Price Range
```bash
curl -X GET "http://localhost:8080/api/search/kibana_sample_data_ecommerce?query=taxful_total_price:%5B100%20TO%20200%5D" \
     -H "Content-Type: application/json"
```

### 3. Search by Category
```bash
curl -X GET "http://localhost:8080/api/search/kibana_sample_data_ecommerce?query=category:Men%27s%20Clothing" \
     -H "Content-Type: application/json"
```

### 4. Combined Search
```bash
curl -X GET "http://localhost:8080/api/search/kibana_sample_data_ecommerce?query=customer_gender:MALE%20AND%20taxful_total_price:%5B100%20TO%20200%5D" \
     -H "Content-Type: application/json"
```

---

## OpenSearch Query Syntax Examples

1. **Exact Match**:
   ```
   customer_gender:MALE
   ```

2. **Range Query**:
   ```
   taxful_total_price:[100 TO 200]
   ```

3. **Multiple Conditions**:
   ```
   customer_gender:MALE AND category:electronics
   ```

4. **Wildcard Search**:
   ```
   customer_full_name:Jo*
   ```

5. **Fuzzy Search**:
   ```
   customer_full_name:Jon~
   ```

---

## Error Handling

The API returns appropriate HTTP status codes. Common responses:

- **200**: Successful operation
- **400**: Bad request (for example, invalid query syntax)
- **404**: Index not found
- **500**: Internal server error

Example Error Response:
```json
{
  "timestamp": "2024-01-22T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid query syntax",
  "path": "/api/search/kibana_sample_data_ecommerce"
}
```

---

## OpenSearch Dashboard

Access the OpenSearch Dashboard at: [http://localhost:5601](http://localhost:5601)

---

## Additional Information

### 1. **Loading Sample Data**
- Access the OpenSearch Dashboard
- Navigate to:
  **Home → Add Sample Data → Add "Sample eCommerce orders"**

### 2. **Environment Variables**
The following environment variables can be configured:
```properties
# OpenSearch connection settings
OPENSEARCH_HOST=localhost
OPENSEARCH_PORT=9200
OPENSEARCH_USERNAME=admin    # Optional: if security is enabled
OPENSEARCH_PASSWORD=admin    # Optional: if security is enabled
```

### 3. **Docker Services**
- **OpenSearch**: [http://localhost:9200](http://localhost:9200)
- **OpenSearch Dashboards**: [http://localhost:5601](http://localhost:5601)
- **Spring Boot Application**: [http://localhost:8080](http://localhost:8080)

---

## Troubleshooting

### 1. **Connection Issues**
Test OpenSearch service:
```bash
curl -u admin:admin http://localhost:9200
```
The request should return OpenSearch cluster information.

### 2. **Index Not Found**
List all available indices:
```bash
curl -u admin:admin http://localhost:9200/_cat/indices
```

### 3. **Common Issues**
- If you see "connection refused" errors, ensure Docker containers are running:
  ```bash
  docker ps
  ```
- If authentication fails, verify your credentials in application.properties
- For permission errors, ensure your user has access to Docker