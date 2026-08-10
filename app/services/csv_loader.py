import csv
from pathlib import Path

from sqlalchemy.orm import Session

from app.config import settings
from app.models.song import SongDetails


def _parse_double(value: str | None) -> float:
    if value is None or not value.strip():
        return 0.0
    return float(value.strip())


def _parse_csv_line(line: str) -> list[str]:
    columns: list[str] = []
    current: list[str] = []
    in_quotes = False

    for character in line:
        if character == '"':
            in_quotes = not in_quotes
        elif character == "," and not in_quotes:
            columns.append("".join(current).strip())
            current = []
        else:
            current.append(character)

    columns.append("".join(current).strip())
    return columns


def load_songs_from_csv(db: Session) -> None:
    if db.query(SongDetails).count() > 0:
        return

    csv_path = Path(settings.csv_path)
    if not csv_path.exists():
        return

    with csv_path.open(newline="", encoding="utf-8") as csv_file:
        reader = csv.reader(csv_file)
        next(reader, None)

        for row in reader:
            if len(row) < 12:
                continue

            try:
                peak_count = row[5].strip() or None
                song = SongDetails(
                    position=int(row[0].strip()),
                    position_change=row[1].strip(),
                    artist_and_title=row[2].strip(),
                    days=_parse_double(row[3]),
                    peak_position=_parse_double(row[4]),
                    peak_count=peak_count,
                    streams=_parse_double(row[6]),
                    streams_change=_parse_double(row[7]),
                    seven_day_streams=_parse_double(row[8]),
                    seven_day_change=_parse_double(row[9]),
                    total_streams=_parse_double(row[10]),
                    country_url=row[11].strip(),
                )
                db.add(song)
            except (ValueError, IndexError):
                continue

    db.commit()
