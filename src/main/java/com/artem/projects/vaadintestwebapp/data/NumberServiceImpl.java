package com.artem.projects.vaadintestwebapp.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NumberServiceImpl implements NumberService {
    private final NumberRepository numberRepository;

    @Override
    public void saveNumber(Number number) {
        numberRepository.save(number);
    }
}
