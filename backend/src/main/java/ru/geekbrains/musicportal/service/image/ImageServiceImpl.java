package ru.geekbrains.musicportal.service.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.image.ImageDto;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.repository.ImageRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveOrUpdate(Image entity) {
        return imageRepository.save(entity);
    }

    @Override
    public Optional<Image> findOneEntityById(Long id) {
        return Optional.of(imageRepository.findById(id,Image.class));
    }

    @Override
    public Collection<ImageDto> findAllDto() {
        return imageRepository.findAllByIdNotNull();
    }


    @Override
    public ImageDto findOneDtoById(Long id) {
        return imageRepository.findOneById(id);
    }

    @Override
    public Image convertToEntity(ImageDto dto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

    public Optional<ImageDto> findByIdDTO(Long id) {
        return Optional.of(imageRepository.findById(id, ImageDto.class));
    }

    public void delete(Image image){
        imageRepository.delete(image);
    }
}
