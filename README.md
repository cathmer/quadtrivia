# Trivia App

## Backend

For the backend Java 17 should be installed. To start the server, open a terminal, go into the `backend` directory, and run:
```
./gradlew bootRun
```

To run the tests, do:
```
./gradlew test
```

The backend will be running at `localhost:8080`.

## Frontend

For the frontend `nodejs` and `npm` should be installed. To start the client, open a terminal, go into the `frontend` directory, and run:
```
npm start
```

This will automatically open the React client at `localhost:3000`. There 5 questions are displayed, which are retrieved from the server. When an answer is selected, it will turn red or green when it's incorrect or correct, respectively.