package com.wsb.SicrediChallenge.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.SicrediChallenge.Model.AdminstradorModel;

@Repository
public interface AdminstradorRepository extends JpaRepository<AdminstradorModel, Long> {
	public Optional<AdminstradorModel> findByUsuario(String usuario);
	
}
