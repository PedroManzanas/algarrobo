package com.desarrollo.algarrobo.service;

import com.desarrollo.algarrobo.entity.Mueble;
import com.desarrollo.algarrobo.repository.MuebleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuebleService {

    private final MuebleRepository muebleRepository;

    public MuebleService(MuebleRepository muebleRepository) {
        this.muebleRepository = muebleRepository;
    }

    public List<Mueble> listarMuebles() {
        return muebleRepository.findAll();
    }

    public Mueble guardarMueble(Mueble mueble) {
        return muebleRepository.save(mueble);
    }

    public Mueble buscarPorId(Long id) {
        return muebleRepository.findById(id).orElse(null);
    }

    public void eliminarMueble(Long id) {
        muebleRepository.deleteById(id);
    }
}