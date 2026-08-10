from fastapi import APIRouter, Depends, HTTPException, Query, status
from sqlalchemy.orm import Session

from app.database import get_db
from app.schemas.song import SongDetailsCreate, SongDetailsResponse, SongDetailsUpdate
from app.services import song_service

router = APIRouter(prefix="/api/v1/song", tags=["songs"])


@router.get("", response_model=list[SongDetailsResponse])
def get_songs(
    country_url: str | None = Query(default=None),
    artist: str | None = Query(default=None),
    position: int | None = Query(default=None),
    peak_count: str | None = Query(default=None),
    db: Session = Depends(get_db),
):
    return song_service.get_songs(
        db,
        country_url=country_url,
        artist=artist,
        position=position,
        peak_count=peak_count,
    )


@router.post("", response_model=SongDetailsResponse, status_code=status.HTTP_201_CREATED)
def add_song(song: SongDetailsCreate, db: Session = Depends(get_db)):
    return song_service.add_song(db, song)


@router.put("", response_model=SongDetailsResponse)
def update_song(updated_song: SongDetailsUpdate, db: Session = Depends(get_db)):
    result = song_service.update_song(db, updated_song)
    if result is None:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Song not found")
    return result


@router.delete("/{song_id}", response_model=str)
def delete_song(song_id: int, db: Session = Depends(get_db)):
    song_service.delete_song(db, song_id)
    return "Song deleted successfully"
