package ar.edu.unq.desapp.grupoM.backenddesappapi.model;

import ar.edu.unq.desapp.grupoM.backenddesappapi.builders.CryptoBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CryptoCurrencyTest {

    ArrayList<CryptoCurrency> cryptos = new ArrayList<CryptoCurrency>();

    @Test
    public void createCryptoCurrencyTest() {
        CryptoCurrency BNBUSDT = CryptoBuilder.crypto().build();
        assertEquals(BNBUSDT.getName(), "BNBUSDT");

    }

}