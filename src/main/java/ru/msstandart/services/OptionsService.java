package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msstandart.dto.PriceDto;
import ru.msstandart.entities.Options;
import ru.msstandart.exceptions.ResourceNotFoundException;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.OptionsRepository;

@Service
@RequiredArgsConstructor
public class OptionsService {

    private final OptionsRepository optionsRepository;

    public PriceDto getPrice() {
       Options options = optionsRepository.findById("Price").orElseThrow(() -> new ResourceNotFoundException("Options not found"));
       return EntityDtoMapper.INSTANCE.toDto(options);
    }

    @Transactional
    public void changePrices(PriceDto priceDto) {
        Options options = optionsRepository.findById("Price").orElseThrow(() -> new ResourceNotFoundException("Options not found"));
        options.setLetterNameCost(priceDto.getLetterNameCost());
        options.setLetterEpitaphCost(priceDto.getLetterEpitaphCost());
        options.setDigitCost(priceDto.getDigitCost());
        optionsRepository.save(options);
    }

}
