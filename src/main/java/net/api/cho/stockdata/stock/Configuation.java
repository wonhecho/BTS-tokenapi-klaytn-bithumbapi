package net.api.cho.stockdata.stock;

import net.api.cho.stockdata.stock.NFT.Domain.NFT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configuation {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public NFT getNFT() { return new NFT(); }
}
