package com.ejemplo.tienda.controller;

import com.ejemplo.tienda.dto.ErrorResponse;
import com.ejemplo.tienda.dto.ProductoDTO;
import com.ejemplo.tienda.validation.ProductoValidador;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoValidador validador;

    public ProductoController(ProductoValidador validador) {
        this.validador = validador;
    }

    @PostMapping("/validar")
    public ResponseEntity<?> validar(@RequestBody ProductoDTO producto) {
        return validador.validar(producto)
                .map(mensaje -> ResponseEntity.badRequest().body(new ErrorResponse(mensaje)))
                .orElseGet(() -> ResponseEntity.ok().build());
    }
}
