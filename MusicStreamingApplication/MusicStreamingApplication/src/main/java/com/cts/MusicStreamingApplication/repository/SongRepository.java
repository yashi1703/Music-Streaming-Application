package com.cts.MusicStreamingApplication.repository;

import com.cts.MusicStreamingApplication.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}


