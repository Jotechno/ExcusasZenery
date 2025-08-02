package com.excusaszenery.service;

import com.excusaszenery.dto.ExcuseRequestDto;
import com.excusaszenery.model.Category;
import com.excusaszenery.model.Excuse;
import com.excusaszenery.model.User;
import com.excusaszenery.repository.CategoryRepository;
import com.excusaszenery.repository.ExcuseRepository;
import com.excusaszenery.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.excusaszenery.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@Service
public class ExcuseServiceImpl implements ExcuseService{
    @Autowired
    private ExcuseRepository excuseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Excuse createExcuse(ExcuseRequestDto dto){
        if (dto.getUserId() == null || dto.getCategoryID() == null) {
            throw new IllegalArgumentException("userId y categoryID no pueden ser null");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Category category = categoryRepository.findById(dto.getCategoryID())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Excuse excuse = new Excuse();
        excuse.setExcuseText(dto.getExcuseText());
        excuse.setUser(user);
        excuse.setCategory(category);

        excuse.setLikes(dto.getLikes() != null ? dto.getLikes() : 0);
        excuse.setDislikes(dto.getDislikes() != null ? dto.getDislikes() : 0);
        excuse.setIsPublic(dto.getIsPublic() != null ? dto.getIsPublic() : true);

        return excuseRepository.save(excuse);
    }

    @Override
    public Excuse updateExcuse(Integer id, Excuse excuse) {
        return null;
    }

    @Override
    public boolean deleteExcuse(Integer id) {
        if (excuseRepository.existsById(id)) {
            excuseRepository.deleteById(id);
            return true;
        }
        throw new ResourceNotFoundException("No existe excusa con ese id.");
    }

    @Override
    public Optional<Excuse> getExcuseById(Integer id) {
        return excuseRepository.findById(id);
    }

    @Override
    public List<Excuse> getAllExcuses() {
        return excuseRepository.findAll();
    }

    @Override
    public boolean toggleExcuseStatus(Integer id) {
        return excuseRepository.findById(id).map(excuse -> {
            excuse.setIsPublic(!excuse.getIsPublic());
            excuseRepository.save(excuse);
            return true;
        }).orElse(false);
    }
}
