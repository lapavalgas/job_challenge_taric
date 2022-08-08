package com.lapavalgas.taricchallenge;

import com.lapavalgas.taricchallenge.domain.DTO;
import com.lapavalgas.taricchallenge.domain.Mapper;
import com.lapavalgas.taricchallenge.domain.repository.CustomerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class TaricchallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaricchallengeApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/graphql/**")
                        .allowedOrigins(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedMethods(CorsConfiguration.ALL);
            }
        };
    }

    @Bean
    ApplicationRunner applicationRunner(CustomerRepository customerRepository) {
        return args -> {
            var d1 = new DTO();
            d1.setCpf("01187963054");
            d1.setNome("Rafael da Silva");
            d1.setEmail("dasilvar@customer.com");
            d1.setTelefone("048988881313");
            d1.setCep("88803140");
            d1.setLogradouro("Rua Thomé de Souza");
            d1.setNumero("N. 2321");
            d1.setComplemento("Na esquinsa com a casa de madeira velha");
            d1.setBairro("Michel");
            d1.setLocalidade("Criciúma");
            d1.setUf("SC");
            d1.setIbge("4204608");
            var c1 = Mapper.dtoToCustomerFullParse(d1);
            customerRepository.save(c1);

            var d2 = new DTO();
            d2.setCpf("03377289097");
            d2.setNome("Paula Curzio");
            d2.setEmail("ulacurzio@customer.com");
            d2.setTelefone("048988760213");
            d2.setCep("88067140");
            d2.setLogradouro("Rua Manoel Vidal");
            d2.setNumero("N. 1A");
            d2.setComplemento("Ed. Azul, Ap. 61");
            d2.setBairro("Pântano do Sul");
            d2.setLocalidade("Florianópolis");
            d2.setUf("SC");
            d2.setIbge("4205407");
            var c2 = Mapper.dtoToCustomerFullParse(d2);
            customerRepository.save(c2);
        };
    }

}
