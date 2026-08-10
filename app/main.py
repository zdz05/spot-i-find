from contextlib import asynccontextmanager

from fastapi import FastAPI

from app.database import Base, SessionLocal, engine
from app.routers.songs import router as songs_router
from app.services.csv_loader import load_songs_from_csv


@asynccontextmanager
async def lifespan(_: FastAPI):
    Base.metadata.create_all(bind=engine)

    db = SessionLocal()
    try:
        load_songs_from_csv(db)
    finally:
        db.close()

    yield


app = FastAPI(
    title="Spot-i-Find API",
    description="Song stats backend powered by FastAPI",
    version="1.0.0",
    lifespan=lifespan,
)

app.include_router(songs_router)


@app.get("/health")
def health_check():
    return {"status": "ok"}
