package com.lapavalgas.taricchallenge.domain.service;

import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.MSG;
import com.lapavalgas.taricchallenge.domain.Mapper;
import com.lapavalgas.taricchallenge.domain.model.*;
import com.lapavalgas.taricchallenge.domain.repository.CEPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class AddressService {

    @Autowired
    CEPRepository cepRepository;

    private final String OPEN_CEP_BASE_URI = "https://opencep.com/v1/";

    private DTO httpRequesterToOpenCep(String cep) {
        var dto = new DTO();
        try {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI(OPEN_CEP_BASE_URI + cep);
            ResponseEntity<DTO> result = restTemplate.getForEntity(uri, DTO.class);
            dto = result.getBody();
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.OPEN_CEP_VALID);
        } catch (Exception e) {
            dto.setCep(cep);
            return dto.setHttpResponse(MSG.STATUS_CODE_404, MSG.OPEN_CEP_INVALID);
        }
    }

    private CEP lookingForCepInOurDatabase(String cep) {
        if (cepRepository.existsByCep(cep)) {
            return cepRepository.findByCep(cep);
        }
        return null;
    }

    @SchemaMapping(typeName = "Mutation", value = "buscaCep")
    public DTO buscaCep(@Argument String cep) {
        if (FakeSessionService.isUserLogged()) {
            var ourCep = lookingForCepInOurDatabase(cep);
            if (ourCep == null) {
                return httpRequesterToOpenCep(cep);
            }
            var dto = Mapper.CEPToDTO(ourCep);
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CEP_ALREADY_IN_OUR_DATABASE);
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_NOT_LOGGED);
    }

    @SchemaMapping(typeName = "Query", value = "enderecosCep")
    public DTO enderecosCep() {
        if (FakeSessionService.isUserLogged()) {
            var dto = Mapper.addressListToDTOListFullParse(cepRepository.findAll());
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.ADDRESS_SUCCESS_TO_LOAD);
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_LOGOFF);
    }
}
