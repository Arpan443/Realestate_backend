# RealEstate — Backend

A full-featured real estate listing platform backend built with Spring Boot. Supports JWT authentication, role-based access control, property CRUD, image/video uploads via Cloudinary, search/filtering, and more.

## Tech Stack

- **Java 21**, **Spring Boot 4.1**
- **Spring Security** with JWT authentication
- **Spring Data JPA** + **PostgreSQL**
- **Cloudinary** for media storage
- **Lombok**, **Bean Validation**
- **Maven**

## Features

- User registration/login with role-based access (`BUYER`, `SELLER`, `AGENT`, `ADMIN`)
- JWT-based stateless authentication
- Property listings with full CRUD operations
- Image/video upload per property via Cloudinary
- Search and filter properties by city, type, and price range
- Owner-only edit/delete permissions
- Global exception handling with clean JSON error responses
- CORS configured for frontend integration

## Project Structure
## Project Structure

src/main/java/com/realestate/RealEstate/
├── config/ # Security, CORS, Cloudinary, Password config
├── Controller/ # REST controllers
├── dto/ # Request/response DTOs
├── Entities/ # JPA entities
├── exception/ # Global exception handler
├── Repository/ # JPA repositories
├── security/ # JWT utilities and filters
└── service/ # Business logic


## Environment Variables

This app requires the following environment variables (no secrets are committed to the repo):

| Variable | Description |
|---|---|
| `DB_URL` | PostgreSQL JDBC connection URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |
| `JWT_SECRET` | Secret key for signing JWT tokens |
| `CLOUDINARY_CLOUD_NAME` | Cloudinary account cloud name |
| `CLOUDINARY_API_KEY` | Cloudinary API key |
| `CLOUDINARY_API_SECRET` | Cloudinary API secret |
| `PORT` | Server port (defaults to 8081 locally) |

## Running Locally

1. Clone the repo
2. Create a PostgreSQL database
3. Set the environment variables above (via your IDE's run configuration or a `.env` mechanism)
4. Run:
```bash
   ./mvnw spring-boot:run
```
5. The API will be available at `http://localhost:8081`

## API Overview

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | No |
| POST | `/auth/login` | Login, returns JWT | No |
| GET | `/properties` | List/search properties | No |
| GET | `/properties/{id}` | Get property details | No |
| POST | `/properties` | Create a property | Yes (SELLER/AGENT) |
| DELETE | `/properties/{id}` | Delete own property | Yes (owner only) |
| POST | `/properties/{id}/media` | Upload image/video | Yes |
| GET | `/properties/my-listings` | Get logged-in user's listings | Yes |



## Deployment
done via railway
