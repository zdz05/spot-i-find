from sqlalchemy import Float, Integer, String
from sqlalchemy.orm import Mapped, mapped_column

from app.database import Base


class SongDetails(Base):
    __tablename__ = "song_details"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, autoincrement=True)
    position: Mapped[int | None] = mapped_column(Integer, nullable=True)
    position_change: Mapped[str | None] = mapped_column(String, nullable=True)
    artist_and_title: Mapped[str | None] = mapped_column(String, nullable=True)
    days: Mapped[float | None] = mapped_column(Float, nullable=True)
    peak_position: Mapped[float | None] = mapped_column(Float, nullable=True)
    peak_count: Mapped[str | None] = mapped_column(String, nullable=True)
    streams: Mapped[float | None] = mapped_column(Float, nullable=True)
    streams_change: Mapped[float | None] = mapped_column(Float, nullable=True)
    seven_day_streams: Mapped[float | None] = mapped_column(Float, nullable=True)
    seven_day_change: Mapped[float | None] = mapped_column(Float, nullable=True)
    total_streams: Mapped[float | None] = mapped_column(Float, nullable=True)
    country_url: Mapped[str | None] = mapped_column(String, nullable=True)
