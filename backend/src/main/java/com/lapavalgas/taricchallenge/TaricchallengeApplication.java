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
            d1.setCpf("00000000000");
            d1.setNome("Rafael Genericuser");
            d1.setEmail("genericuser@customer.com");
            d1.setTelefone("(000) 000000000");
            d1.setCep("00000000");
            d1.setLogradouro("Av. Ficção");
            d1.setNumero("N. 100");
            d1.setComplemento("Bloco 1000, Ap. 100");
            d1.setBairro("Carambolas");
            d1.setLocalidade("Abacaxi");
            d1.setUf("df");
            d1.setIbge("1000");
            var c1 = Mapper.dtoToCustomerFullParse(d1);
            customerRepository.save(c1);
        };
    }

}
