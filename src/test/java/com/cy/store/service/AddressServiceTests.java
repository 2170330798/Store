package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class AddressServiceTests {

    @Autowired
	private IAddressService addressService;

    @Test
	public void addNewAddress() {
        Address address = new Address();
		address.setPhone("12362371957");
	    address.setName("TQEWE");
		addressService.addNewAddress(1,"π‹¿Ì‘±",address);
	}

	@Test
	public void delete(){
		addressService.delete(9,15,"Manager");
	}
}
