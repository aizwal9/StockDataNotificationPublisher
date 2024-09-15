# Stock Market Tracker
A real-time stock market tracking application built with Spring Boot, React, and GCP Pub/Sub. The application allows users to view stock data and visualizations, bookmark stocks, and receive desktop notifications when bookmarked stocks reach set benchmarks.
## Features:
- Real-time stock data ingestion and storage in MongoDB
- Visualizations using React and Chart.js
- Desktop notifications for bookmarked stocks using GCP Pub/Sub
- Integration with external APIs for latest data

## Architecture:
The architecture diagram (above) shows the components and interactions of the application, including:
- Spring Boot API for data ingestion and storage
- MongoDB for storing raw stock data
- GCP Pub/Sub for sending and receiving messages
- React-based dashboard for visualizing stock data
- Vantage Service for fetching latest data
- Notification Service for desktop notifications

## Technologies:
- Java, Spring Boot
- React, recharts
- MongoDB
- GCP Pub/Sub
- Vantage API

<img width="975" alt="image" src="https://github.com/user-attachments/assets/70addada-4077-44fb-8ba4-d5f872b808e2">
