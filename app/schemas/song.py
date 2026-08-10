from pydantic import BaseModel, ConfigDict, Field


class SongDetailsBase(BaseModel):
    position: int | None = None
    position_change: str | None = None
    artist_and_title: str | None = None
    days: float | None = None
    peak_position: float | None = None
    peak_count: str | None = None
    streams: float | None = None
    streams_change: float | None = None
    seven_day_streams: float | None = None
    seven_day_change: float | None = None
    total_streams: float | None = None
    country_url: str | None = None


class SongDetailsCreate(SongDetailsBase):
    pass


class SongDetailsUpdate(SongDetailsBase):
    id: int


class SongDetailsResponse(SongDetailsBase):
    model_config = ConfigDict(from_attributes=True)

    id: int
