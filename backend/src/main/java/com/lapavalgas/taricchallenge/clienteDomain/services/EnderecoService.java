package com.lapavalgas.taricchallenge.clienteDomain.services;

import com.lapavalgas.taricchallenge.clienteDomain.entities.DTO;
import com.lapavalgas.taricchallenge.clienteDomain.entities.EnderecoCEP;
import com.lapavalgas.taricchallenge.clienteDomain.entities.MSG;
import com.lapavalgas.taricchallenge.clienteDomain.entities.Mapper;
import com.lapavalgas.taricchallenge.clienteDomain.repositories.EnderecoCEPRepository;
import com.lapavalgas.taricchallenge.session.FakeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
public class EnderecoService {

    @Autowired
    EnderecoCEPRepository enderecoCEPRepository;

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

    private EnderecoCEP lookingForCepInOurDatabase(String cep) {
        if (enderecoCEPRepository.existsByCep(cep)) {
            return enderecoCEPRepository.findByCep(cep);
        }
        return null;
    }

    @SchemaMapping(typeName = "Mutation", value = "addressByCEP")
    public DTO addressByCEP(@Argument String cep) throws URISyntaxException {
        if (!FakeSession.isUserLogged()) {
            var ourEnderecoCep = lookingForCepInOurDatabase(cep);
            if (ourEnderecoCep == null) {
                return httpRequesterToOpenCep(cep);
            }
            var dto = Mapper.enderecoCEPToDTO(ourEnderecoCep);
            return dto.setHttpResponse(MSG.STATUS_CODE_200, MSG.CEP_ALREADY_IN_OUR_DATABASE);
        }
        var dto = new DTO();
        return dto.setHttpResponse(MSG.STATUS_CODE_400, MSG.USER_NOT_LOGGED);
    }

}
