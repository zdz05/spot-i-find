from sqlalchemy.orm import Session

from app.models.song import SongDetails
from app.schemas.song import SongDetailsCreate, SongDetailsUpdate


def get_songs(
    db: Session,
    country_url: str | None = None,
    artist: str | None = None,
    position: int | None = None,
    peak_count: str | None = None,
) -> list[SongDetails]:
    query = db.query(SongDetails)

    if country_url is not None and position is not None:
        return query.filter(
            SongDetails.country_url == country_url,
            SongDetails.position == position,
        ).all()
    if country_url is not None:
        return query.filter(SongDetails.country_url == country_url).all()
    if artist is not None:
        return query.filter(
            SongDetails.artist_and_title.ilike(f"%{artist}%")
        ).all()
    if position is not None:
        return query.filter(SongDetails.position == position).all()
    if peak_count is not None:
        return query.filter(SongDetails.peak_count.ilike(f"%{peak_count}%")).all()

    return query.all()


def add_song(db: Session, song: SongDetailsCreate) -> SongDetails:
    db_song = SongDetails(**song.model_dump())
    db.add(db_song)
    db.commit()
    db.refresh(db_song)
    return db_song


def update_song(db: Session, updated_song: SongDetailsUpdate) -> SongDetails | None:
    db_song = db.query(SongDetails).filter(SongDetails.id == updated_song.id).first()
    if db_song is None:
        return None

    for field, value in updated_song.model_dump(exclude={"id"}).items():
        setattr(db_song, field, value)

    db.commit()
    db.refresh(db_song)
    return db_song


def delete_song(db: Session, song_id: int) -> None:
    db.query(SongDetails).filter(SongDetails.id == song_id).delete()
    db.commit()
