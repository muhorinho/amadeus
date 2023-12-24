# Amadeus Flight Search Application QA Tests

This repository contains automated test scripts for the Amadeus Flight Search Application, both for frontend and backend components.

## Overview

The purpose of these tests is to ensure the reliability and functionality of the Amadeus Flight Search Application. This includes verifying the backend API responses and frontend user interface operations.

## Test Cases

### Backend Tests

- Verify that the API responds with a 200 status code for GET requests.
- Ensure the response structure matches the expected format containing `id`, `from`, `to`, and `date` fields.
- Check that the `Content-Type` header in the response is `application/json`.

### Frontend Tests

- Test that the same values cannot be entered in both "From" and "To" fields in the flight search application.
- Verify the number of flights listed matches the count indicated in the application for specific city pairs, such as from Istanbul to Los Angeles.

## Issues and Contributions

For any issues or suggestions, please contact [muhammedbuga@posta.mu.edu.tr](mailto:muhammedbuga@posta.mu.edu.tr).

