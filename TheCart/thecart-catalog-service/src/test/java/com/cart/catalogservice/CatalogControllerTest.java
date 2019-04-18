package com.cart.catalogservice;

import com.cart.catalogservice.resources.controller.CatalogController;
import com.cart.catalogservice.resources.model.Product;
import com.cart.catalogservice.resources.service.CatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.nio.file.Files;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class CatalogControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private CatalogController catalogController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(catalogController).build();
    }

    @Test
    public void saveProduct() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("product1.json").getFile());
        String content = new String(Files.readAllBytes(file.toPath()));
        Product product = toObj(content, Product.class);
        when(catalogService.saveProduct(product)).thenReturn(product);
        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is("Shirt")));
    }

    @Test
    public void getProductById() throws Exception {
        mockMvc.perform(get("/api/product/{id}", -1L))
                .andExpect(status().isNotFound());
        verify(catalogService, times(1)).getProductById(-1L);


    }

    public static <T> T toObj(String json, Class<T> cl) throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, cl);
    }
}
