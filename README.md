# Spot-i-Find Backend

Python FastAPI backend for song stats, containerized with Docker and ready to connect to GitHub.

## Stack

- **FastAPI** — REST API
- **PostgreSQL** — database
- **SQLAlchemy** — ORM
- **Docker / Docker Compose** — local and deployment runtime

## Quick start

### Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (includes Docker Compose)

### Run with Docker

```bash
docker compose up --build
```

The API will be available at:

- API: http://localhost:8000
- Interactive docs: http://localhost:8000/docs
- Health check: http://localhost:8000/health

PostgreSQL is exposed on port **5431** (mapped from container port 5432).

### Stop

```bash
docker compose down
```

To remove the database volume as well:

```bash
docker compose down -v
```

## API endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/v1/song` | List songs (optional filters: `country_url`, `artist`, `position`, `peak_count`) |
| POST | `/api/v1/song` | Create a song |
| PUT | `/api/v1/song` | Update a song |
| DELETE | `/api/v1/song/{id}` | Delete a song |
| GET | `/health` | Health check |

On first startup, songs are seeded from `data/combined_spotify_songs.csv`.

## Local development (without Docker)

```bash
python -m venv .venv
source .venv/bin/activate   # Windows: .venv\Scripts\activate
pip install -r requirements.txt
cp .env.example .env
```

Start PostgreSQL (via Docker or locally), then:

```bash
uvicorn app.main:app --reload --port 8000
```

## Environment variables

Copy `.env.example` to `.env` and adjust as needed:

| Variable | Description | Default |
|----------|-------------|---------|
| `DATABASE_URL` | PostgreSQL connection string | `postgresql://postgres:postgres@localhost:5431/songs` |
| `CSV_PATH` | Path to seed CSV file | `data/combined_spotify_songs.csv` |

## Connect to GitHub

1. Create a new repository on GitHub (e.g. `spot-i-find-backend`).

2. Initialize git and push (if not already connected):

```bash
git init
git add .
git commit -m "Add FastAPI backend with Docker"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/spot-i-find-backend.git
git push -u origin main
```

3. GitHub Actions CI runs automatically on push/PR to `main` — it verifies imports and builds the Docker image.

## Project structure

```
app/
  main.py           # FastAPI app entry point
  config.py         # Settings from environment
  database.py       # SQLAlchemy setup
  models/           # Database models
  schemas/          # Pydantic request/response schemas
  routers/          # API route handlers
  services/         # Business logic and CSV seeding
data/
  combined_spotify_songs.csv
Dockerfile
docker-compose.yml
requirements.txt
```

## Deploying

Build and run the image anywhere Docker is supported:

```bash
docker build -t spot-ifind-api .
docker run -p 8000:8000 \
  -e DATABASE_URL=postgresql://user:pass@host:5432/songs \
  spot-ifind-api
```

For production, set `DATABASE_URL` to your managed PostgreSQL instance and run behind a reverse proxy with HTTPS.
