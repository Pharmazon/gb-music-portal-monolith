package ru.geekbrains.musicportal.service.images;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.musicportal.dto.ImageDto;
import ru.geekbrains.musicportal.entity.images.Image;
import ru.geekbrains.musicportal.repository.ImageRepository;

import java.util.Optional;

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

    public Optional<ImageDto> findByIdDTO(Long id) {
        return Optional.of(imageRepository.findById(id,ImageDto.class));
    }

    public void delete(Image image){
        imageRepository.delete(image);
    }
}
