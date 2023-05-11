package ru.msstandart.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msstandart.dto.PostDto;
import ru.msstandart.entities.Post;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostDto postDto) {
        Post post = EntityDtoMapper.INSTANCE.toEntity(postDto);
        postRepository.save(post);
    }
    @Transactional(readOnly = true)
    public List<PostDto> findAllPosts() {
        return postRepository.findAll().stream().map(EntityDtoMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
