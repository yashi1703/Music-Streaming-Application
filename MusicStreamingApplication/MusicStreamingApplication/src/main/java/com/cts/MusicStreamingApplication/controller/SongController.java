package com.cts.MusicStreamingApplication.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.MusicStreamingApplication.model.Song;
import com.cts.MusicStreamingApplication.service.SongService;


@RestController
@RequestMapping("/api/songs")
public class SongController {
	private final SongService songService;
	@Autowired
	public SongController(SongService songService) {
		this.songService=songService;
	}
	
	@GetMapping
	public ResponseEntity<List<Song>> getAllSongs() {
		List<Song> songs= songService.getAllSongs();
		return ResponseEntity.ok(songs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Song> getSongById(@PathVariable Long id) {
		Song song=songService.getSongById(id);
		if(song==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(song);
	}
	
	@PostMapping
	public ResponseEntity createSong(@RequestBody Song song) {
		Song createSong= songService.createSong(song);
		return ResponseEntity.ok("Song Created Successfully");
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
		Song song=songService.getSongById(id);
		if(song==null) {
			return ResponseEntity.notFound().build();
		}
		//Update the existing song with new data
		song.setTitle(updatedSong.getTitle());
		song.setArtist(updatedSong.getArtist());
		
		//Save the updated song
		Song updated=songService.updateSong(id,song);
        return ResponseEntity.ok(updated);

	}
	


	@DeleteMapping("/{id}")
	public ResponseEntity<Song> deleteSong(@PathVariable Long id) {
		Song song=songService.getSongById(id);
		if(song!=null) {
			songService.deleteSong(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}
	


