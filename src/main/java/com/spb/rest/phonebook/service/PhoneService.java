package com.spb.rest.phonebook.service;

import com.spb.rest.phonebook.dto.PhoneDTO;
import com.spb.rest.phonebook.entity.PhoneEntity;
import com.spb.rest.phonebook.error.InstanceNotFoundException;
import com.spb.rest.phonebook.repository.PhoneRepository;
import com.spb.rest.phonebook.service.mapper.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private PhoneMapper phoneMapper;
    @Autowired
    public PhoneService(PhoneRepository phoneRepository, PhoneMapper phoneMapper) {
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
    }

    public List<PhoneDTO> getAllPhones() {
        List<PhoneEntity> phoneEntities = this.phoneRepository.findAll();
        List<PhoneDTO> dtos = new ArrayList<>();
        for (PhoneEntity phoneEntity : phoneEntities) {
            PhoneDTO phoneDTO = this.phoneMapper.toDTO(phoneEntity);
            dtos.add(phoneDTO);
        }
        return dtos;
    }

    public PhoneDTO getPhone (Long id) {
        PhoneEntity phoneEntity = this.phoneRepository.findById(id)
                .orElseThrow(() -> new InstanceNotFoundException(id));
        return this.phoneMapper.toDTO(phoneEntity);
    }

    public PhoneDTO savePhone(PhoneDTO phoneDTO) {
        PhoneEntity phone = this.phoneMapper.toEntity(phoneDTO);
        PhoneEntity savedPhone = this.phoneRepository.save(phone);
        return this.phoneMapper.toDTO(savedPhone);
    }

    public PhoneDTO updatePhone (PhoneDTO phoneDTO, Long id) {
        PhoneEntity phoneEntity = phoneRepository.findById(id)
                .map(phone -> {
                    phone.setPersonid(phoneDTO.id());
                    phone.setPhone(phoneDTO.phone());
                    phone.setType(phoneDTO.type());
                    return this.phoneRepository.save(phone);
                })
                .orElseGet(() -> {
                    PhoneEntity phoneNew = this.phoneMapper.toEntity(phoneDTO);
                    phoneNew.setId(id);
                    return phoneRepository.save(phoneNew);
                });
        return this.phoneMapper.toDTO(phoneEntity);

    }

    public void deletePhone (Long id) {
        this.phoneRepository.deleteById(id);
    }
}
