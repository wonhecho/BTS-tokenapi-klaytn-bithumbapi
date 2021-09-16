package net.api.cho.stockdata.stock.Price;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface PriceService {
    Object init() throws IOException, ParseException;
}
