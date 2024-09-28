# Running Book Library in Docker

This document provides instructions on how to run the Book Library application in a Docker environment.

## Prerequisites

- Docker installed
- Docker Compose installed

## Steps to Run

1. Build the project using Maven:

   ```
   mvn clean package
   ```

2. Ensure that the `books.json` file exists in the project's root directory. If not, create it with sample content:

   ```json
   [
     {
       "id": 1,
       "title": "Clean Code",
       "author": "Robert C. Martin",
       "year": 2008,
       "rating": 4.8,
       "numberOfRatings": 1
     }
   ]
   ```

3. Build the Docker image:

   ```
   docker build -t booklibrary .
   ```

4. Run the container using Docker Compose:

   ```
   docker-compose up
   ```

5. The application should now be accessible at `http://localhost:8080`

## Stopping the Application

To stop the application, use the `Ctrl+C` key combination in the terminal where you ran `docker-compose up`, or execute the command:

```
docker-compose down
```

## Troubleshooting

- If you have issues accessing the `books.json` file, make sure the file exists in the project's root directory and has the appropriate permissions.
- If port 8080 is already in use on your computer, change the port mapping in the `docker-compose.yml` file.

## Additional Notes

- The application uses a volume to persist the `books.json` file. This means that any changes made to the books will be saved even if the container is stopped and restarted.
- To run the application in detached mode (in the background), use the command `docker-compose up -d`.
- To view the logs of a detached container, use `docker-compose logs -f`.