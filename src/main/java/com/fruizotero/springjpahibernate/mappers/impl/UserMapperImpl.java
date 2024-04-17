package com.fruizotero.springjpahibernate.mappers.impl;

import com.fruizotero.springjpahibernate.domain.dto.UserDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(userEntityToUserDtoMap());
    }

    private PropertyMap<UserEntity, UserDto> userEntityToUserDtoMap() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                using(roleListToIntegerListConverter())
                        .map(source.getRoles(), destination.getRoles());
            }
        };
    }

    private Converter<List<RoleEntity>, List<Integer>> roleListToIntegerListConverter() {


        return context -> context.getSource().stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList());
    }


    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
