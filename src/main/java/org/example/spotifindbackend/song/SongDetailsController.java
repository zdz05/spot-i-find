package org.example.spotifindbackend.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/song")
public class SongDetailsController {
    private final SongDetailsService songDetailsService;

    @Autowired
    public SongDetailsController(SongDetailsService songDetailsService) {
        this.songDetailsService = songDetailsService;
    }

    @GetMapping
    public List<SongDetails> getSongs(
            @RequestParam(required = false) String countryUrl,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) Integer position,
            @RequestParam(required = false) String peakCount) {

        if (countryUrl != null && position != null) {
            return songDetailsService.getSongsByCountryUrlAndPosition(countryUrl, position);
        } else if (countryUrl != null) {
            return songDetailsService.getSongsByCountryUrl(countryUrl);
        } else if (artist != null) {
            return songDetailsService.getSongsByArtist(artist);
        } else if (position != null) {
            return songDetailsService.getSongsByPosition(position);
        } else if (peakCount != null) {
            return songDetailsService.getSongsByPeakCount(peakCount);
        } else {
            return songDetailsService.getSongs();
        }
    }

    @PostMapping
    public ResponseEntity<SongDetails> addSong(@RequestBody SongDetails song) {
        SongDetails createdSong = songDetailsService.addSong(song);
        return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SongDetails> updateSong(@RequestBody SongDetails updatedSong) {
        SongDetails resultSong = songDetailsService.updateSong(updatedSong);
        if (resultSong != null) {
            return new ResponseEntity<>(resultSong, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        songDetailsService.deleteSong(id);
        return new ResponseEntity<>("Song deleted successfully", HttpStatus.OK);
    }
}
