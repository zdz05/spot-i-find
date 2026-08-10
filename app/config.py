from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    model_config = SettingsConfigDict(env_file=".env", env_file_encoding="utf-8")

    database_url: str = "postgresql://postgres:postgres@db:5432/songs"
    csv_path: str = "data/combined_spotify_songs.csv"


settings = Settings()
