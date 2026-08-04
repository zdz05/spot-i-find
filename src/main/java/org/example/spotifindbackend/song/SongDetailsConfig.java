package org.example.spotifindbackend.song;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SongDetailsConfig {

    @Bean
    CommandLineRunner commandLineRunner(SongDetailsRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            ClassPathResource resource = new ClassPathResource("combined_spotify_songs.csv");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                reader.readLine();

                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] cols = parseCsvLine(line);
                        if (cols.length < 12) {
                            continue;
                        }

                        String peakCount = cols[5].trim();
                        if (peakCount.isEmpty()) {
                            peakCount = null;
                        }

                        SongDetails song = new SongDetails(
                                Integer.parseInt(cols[0].trim()),
                                cols[1].trim(),
                                cols[2].trim(),
                                parseDouble(cols[3]),
                                parseDouble(cols[4]),
                                peakCount,
                                parseDouble(cols[6]),
                                parseDouble(cols[7]),
                                parseDouble(cols[8]),
                                parseDouble(cols[9]),
                                parseDouble(cols[10]),
                                cols[11].trim()
                        );

                        repository.save(song);
                    } catch (Exception ignored) {
                    }
                }
            }
        };
    }

    private String[] parseCsvLine(String line) {
        List<String> columns = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);

            if (character == '"') {
                inQuotes = !inQuotes;
            } else if (character == ',' && !inQuotes) {
                columns.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(character);
            }
        }

        columns.add(current.toString().trim());
        return columns.toArray(new String[0]);
    }

    private double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(value.trim());
    }
}
