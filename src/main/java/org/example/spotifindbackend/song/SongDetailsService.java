package org.example.spotifindbackend.song;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SongDetailsService {

    private final SongDetailsRepository songDetailsRepository;

    @Autowired
    public SongDetailsService(SongDetailsRepository songDetailsRepository) {
        this.songDetailsRepository = songDetailsRepository;
    }

    public List<SongDetails> getSongs() {
        return songDetailsRepository.findAll();
    }

    public List<SongDetails> getSongsByCountryUrl(String countryUrl) {
        return songDetailsRepository.findAll().stream()
                .filter(song -> countryUrl.equals(song.getCountryUrl()))
                .collect(Collectors.toList());
    }

    public List<SongDetails> getSongsByArtist(String searchText) {
        return songDetailsRepository.findAll().stream()
                .filter(song -> song.getArtistAndTitle().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<SongDetails> getSongsByPosition(int position) {
        return songDetailsRepository.findAll().stream()
                .filter(song -> song.getPosition() == position)
                .collect(Collectors.toList());
    }

    public List<SongDetails> getSongsByPeakCount(String searchText) {
        return songDetailsRepository.findAll().stream()
                .filter(song -> song.getPeakCount().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<SongDetails> getSongsByCountryUrlAndPosition(String countryUrl, int position) {
        return songDetailsRepository.findAll().stream()
                .filter(song -> countryUrl.equals(song.getCountryUrl()) && song.getPosition() == position)
                .collect(Collectors.toList());
    }

    public SongDetails addSong(SongDetails song) {
        songDetailsRepository.save(song);
        return song;
    }

    public SongDetails updateSong(SongDetails updatedSong) {
        Optional<SongDetails> existingSong = songDetailsRepository.findById(updatedSong.getId());

        if (existingSong.isPresent()) {
            SongDetails songToUpdate = existingSong.get();
            songToUpdate.setPosition(updatedSong.getPosition());
            songToUpdate.setPositionChange(updatedSong.getPositionChange());
            songToUpdate.setArtistAndTitle(updatedSong.getArtistAndTitle());
            songToUpdate.setDays(updatedSong.getDays());
            songToUpdate.setPeakPosition(updatedSong.getPeakPosition());
            songToUpdate.setPeakCount(updatedSong.getPeakCount());
            songToUpdate.setStreams(updatedSong.getStreams());
            songToUpdate.setStreamsChange(updatedSong.getStreamsChange());
            songToUpdate.setSevenDayStreams(updatedSong.getSevenDayStreams());
            songToUpdate.setSevenDayChange(updatedSong.getSevenDayChange());
            songToUpdate.setTotalStreams(updatedSong.getTotalStreams());
            songToUpdate.setCountryUrl(updatedSong.getCountryUrl());
            songDetailsRepository.save(songToUpdate);
            return songToUpdate;
        }
        return null;
    }

    @Transactional
    public void deleteSong(Long id) {
        songDetailsRepository.deleteById(id);
    }
}
