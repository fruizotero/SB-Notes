package com.fruizotero.springjpahibernate.mappers.impl;

import com.fruizotero.springjpahibernate.domain.dto.NoteDto;
import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.repositories.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class NoteMapperImpl implements Mapper<NoteEntity, NoteDto> {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public NoteMapperImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.modelMapper.addMappings(noteEntityToNoteDtoMap());
        this.modelMapper.addMappings(noteDtoToNoteEntityMap());
    }


    private PropertyMap<NoteEntity, NoteDto> noteEntityToNoteDtoMap() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                map(source.getUserId().getId(), destination.getUserId());
            }
        };
    }


    private PropertyMap<NoteDto, NoteEntity> noteDtoToNoteEntityMap() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setTitle(source.getTitle());
                map().setContent(source.getContent());
                using(userEntityConverter()).map(source, destination.getUserId());
            }
        };
    }

    private Converter<NoteDto, UserEntity> userEntityConverter() {
        return context -> userRepository.findById(context.getSource().getUserId()).orElse(null);
    }


    @Override
    public NoteDto mapTo(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDto.class);
    }

    @Override
    public NoteEntity mapFrom(NoteDto noteDto) {
        return modelMapper.map(noteDto, NoteEntity.class);
    }
}
