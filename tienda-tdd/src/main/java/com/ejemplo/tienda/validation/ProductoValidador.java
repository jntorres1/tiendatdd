package com.ejemplo.tienda.validation;

import com.ejemplo.tienda.dto.ProductoDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductoValidador {

    public Optional<String> validar(ProductoDTO producto) {
        if (producto.getPrecio() < 0) {
            return Optional.of("El precio no puede ser negativo");
        }
        return Optional.empty();
    }
}
