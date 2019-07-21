package ru.geekbrains.musicportal.service.image;

import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
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
    public Collection<ImageDto> findAllDtos() {
        return imageRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Image> findAll() {
        return (Collection<Image>) imageRepository.findAll();
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
    public ImageDto convertToDto(Image entity) {
        return modelMapper.map(entity, ImageDto.class);
    }

    public boolean deleteById(Long id) {
        imageRepository.deleteById(id);
        return true;
    }

}
