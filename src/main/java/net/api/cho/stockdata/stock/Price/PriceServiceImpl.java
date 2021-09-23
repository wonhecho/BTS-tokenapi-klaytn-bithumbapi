package net.api.cho.stockdata.stock.Price;

import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService{
    private final Priceapi priceapi;
    @Cacheable(key="1",value = "total")
    public Object init() throws IOException, ParseException {
        return priceapi.callApi();
    }
}
