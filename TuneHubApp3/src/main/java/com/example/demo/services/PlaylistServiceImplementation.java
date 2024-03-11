package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;
@Service
public class PlaylistServiceImplementation implements PlaylistService {
	
	@Autowired
	PlaylistRepository prepo;
	

	@Override
	public void addPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		prepo.save(playlist);
		
	}


	@Override
	public List<Playlist> fetchPlaylists() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}

}
