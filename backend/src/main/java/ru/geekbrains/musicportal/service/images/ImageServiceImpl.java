package ru.geekbrains.musicportal.service.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.image.ImageDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.repository.ImageRepository;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(Image entity) {
        return imageRepository.save(entity);
    }

    @Override
    public Optional<Image> findById(Long id) {
        return Optional.of(imageRepository.findById(id,Image.class));
    }

    @Override
    public Iterable<Comment> findAll() {
        return null;
    }

    @Override
    public Image convertToEntity(ImageDto dto) {
        return null;
    }

    public Optional<ImageDto> findByIdDTO(Long id) {
        return Optional.of(imageRepository.findById(id, ImageDto.class));
    }

    public void delete(Image image){
        imageRepository.delete(image);
    }
}
