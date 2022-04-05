package com.tripmanagement.asdc.service;

import com.tripmanagement.asdc.dao.CustomerDAO;
import com.tripmanagement.asdc.dao.CustomerDAOImpl;
import com.tripmanagement.asdc.model.Customer;
import com.tripmanagement.asdc.model.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class CustomerServiceImplTest {

    @Autowired
    CustomerServiceImpl customerService;

    @Mock
    CustomerDAO customerDAO;
    @Mock
    CustomerDAOImpl customerDAOImpl;
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testSaveNullCustomer() {
        assertFalse(customerService.saveCustomer(null));
    }

    @Test
    void testSaveEmptyCustomer() {
        assertFalse(customerService.saveCustomer(new User()));
    }

    @Test
    void testSaveCustomerCorrect() {

        User user = new User();
        user.setFirst_name("test");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("1234563");
        user.setUserType("CUSTOMER");

        assertTrue(customerService.saveCustomer(user));
    }
    @Test
    void testSaveCustomerException() {

        User user = new User();
        user.setFirst_name("test\'");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("1234563");
        user.setUserType("CUSTOMER");

        assertFalse(customerService.saveCustomer(user));
    }

    @Test
    void testSaveCustomerIllegaleName() {

        User user = new User();
        user.setFirst_name("test\'");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("123456");
        user.setUserType("CUSTOMER");
        Customer customer = new Customer();
        customer.setCustomer_fname("Sania");
        customer.setCustomer_id(1);
        customer.setCustomer_lname("kumar");
        customer.setEmail("someemail@dal.ca");
        customer.setPassword("password");
        when(customerDAO.saveCustomer(customer)).thenReturn(false);
        boolean result = customerService.saveCustomer(user);
        assertFalse(result);
    }

    @Test
    void testGetCustomerByCorrectEmail() {
        Customer customer = new Customer();
        customer.setCustomer_fname("Sania");
        customer.setCustomer_id(1);
        customer.setCustomer_lname("kumar");
        customer.setEmail("testing@case.com");
        customer.setPassword("password");
        String email= "testing@case.com";
        when(customerDAO.getCustomerByEmail(email)).thenReturn(customer);
        Customer result = customerService.getCustomerByEmail(email);
        assertTrue(result!=null);
    }

    @Test
    void testgetCustomerByEmailException(){
        assertTrue(customerService.getCustomerByEmail("hhjdhs\'")==null);
    }
    @Test
    void testGetCustomerByIllegalEmail() {
        String email= "svt@gmail.com\'";
        when(customerDAO.getCustomerByEmail(email)).thenReturn(null);
        Customer result = customerService.getCustomerByEmail(email);
        assertNull(result);
    }

    @Test
    void testGetCustomerByWrongEmail() {
        String email= "fjvnfk.vfjnvjf";
        when(customerDAO.getCustomerByEmail(email)).thenReturn(null);
        Customer result = customerService.getCustomerByEmail(email);
        assertNull(result);
    }

    @Test
    void testGetCustomerByNullEmail() {
        String email= null;
        when(customerDAO.getCustomerByEmail(email)).thenReturn(null);
        Customer result = customerService.getCustomerByEmail(email);
        assertNull(result);
       // assertNull(customerService.getCustomerByEmail(null));
    }

    @Test
    void testGetCustomerByEmptyEmail() {
        String email= "";
        when(customerDAO.getCustomerByEmail(email)).thenReturn(null);
        Customer result = customerService.getCustomerByEmail(email);
        assertNull(result);
       // assertNull(customerService.getCustomerByEmail(""));
    }

    @Test
    void testGetCustomerByWrongId(){
        assertEquals(customerService.getCustomerById(0),null);
    }
    @Test
    void testGetCustomerByCorrectId(){
        int id = 41;
        Customer customer = new Customer();
        customer.setCustomer_fname("Sania");
        customer.setCustomer_id(1);
        customer.setCustomer_lname("kumar");
        customer.setEmail("test@gmail.com");
        customer.setPassword("password");
        when(customerDAO.getCustomerById(id)).thenReturn(customer);
        Customer result= customerService.getCustomerById(id);
//        assertNotNull(customerService.getCustomerById(1));
        assertTrue(result!=null);
    }

    @Test
    void testupdateAvaialableCredits(){
        int customer_id = 41;
        Customer customer = new Customer();
        customer.setCustomer_fname("Sania");
        customer.setCustomer_id(1);
        customer.setCustomer_lname("kumar");
        customer.setEmail("test@gmail.com");
        customer.setPassword("password");
        when(customerDAO.getCustomerById(customer_id)).thenReturn(customer);
        when(customerDAO.updateAvaialableCredits(customer_id,100)).thenReturn(true);
        boolean result = customerService.buyCredits(customer_id,100);
        assertTrue(result);
        // assertTrue(customerService.buyCredits(9,22));
    }
}