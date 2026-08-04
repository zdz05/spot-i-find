package org.example.spotifindbackend.song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongDetailsRepository extends JpaRepository<SongDetails, Long> {
    void deleteById(Long id);

    Optional<SongDetails> findById(Long id);
}
