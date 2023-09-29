package com.cts.MusicStreamingApplication.service;


import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.MusicStreamingApplication.repository.SongRepository;
import com.cts.MusicStreamingApplication.model.Song;

@Service
public class SongService {
	private final SongRepository songRepository;
	@Autowired
	public SongService(SongRepository songRepository) {
		this.songRepository=songRepository;
	}
    public List<Song> getAllSongs() {
    	return songRepository.findAll();
    }
    public Song getSongById(Long id) {
    	// Implement logic to get a song by Id
    	return songRepository.findById(id).orElse(null);
    }
    public Song createSong(Song song) {
    	// Implement logic to create a new song
    	return songRepository.save(song);
    } 
    public Song updateSong(Long id, Song updatedSong) {
    	// Implement logic to update a song by ID
    	// Ensure the song with given id exists, then update its properties and save it
    	Optional<Song> optionalSong = songRepository.findById(id);
    	if(optionalSong.isPresent()) {
    		Song song=optionalSong.get();
    		//Update the properties of the existing song
    		song.setTitle(updatedSong.getTitle());
    		song.setArtist(updatedSong.getArtist());
    		
    		//Save and return the updated song
    		return songRepository.save(song);
    	} else {
    		// Handle the case where the song with the given ID is not found
    		// You can throw an exception or return null, depending on your requirements
    		return null;
    	}
    	
    	
    } 
    public void deleteSong(Long id) {
    	// Implement logic to delete a song by ID
    	songRepository.deleteById(id);
    } 
}

