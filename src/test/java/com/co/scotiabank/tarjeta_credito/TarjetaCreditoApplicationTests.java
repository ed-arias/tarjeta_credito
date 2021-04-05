package com.co.scotiabank.tarjeta_credito;

import com.co.scotiabank.tarjeta_credito.domain.services.ListasRestrictivasService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TarjetaCreditoApplicationTests {

	@Autowired
	ListasRestrictivasService listas;

	@Test
	void contextLoads() {

		System.out.println(listas.evaluarListasRestrictivas());
	}

}
