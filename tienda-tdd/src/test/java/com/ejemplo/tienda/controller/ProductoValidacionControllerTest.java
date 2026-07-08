package com.ejemplo.tienda.controller;

import com.ejemplo.tienda.dto.ProductoDTO;
import com.ejemplo.tienda.validation.ProductoValidador;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
@Import(ProductoValidador.class)

class ProductoValidacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deberiaRechazarProductoConPrecioNegativo() throws Exception {
        ProductoDTO producto = new ProductoDTO("Camiseta", -10.0);

        mockMvc.perform(post("/api/productos/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El precio no puede ser negativo"));
    }

    @Test
    void deberiaAceptarProductoConPrecioValido() throws Exception {
        ProductoDTO producto = new ProductoDTO("Camiseta", 25.0);

        mockMvc.perform(post("/api/productos/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk());
    }
}