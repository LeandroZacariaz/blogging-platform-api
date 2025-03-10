package com.blogplatform.blogging_api.controller.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.blogplatform.blogging_api.dto.category.CategoryCreateDto;
import com.blogplatform.blogging_api.dto.category.CategoryDto;
import com.blogplatform.blogging_api.service.category.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
@Tag(name = "Categorías", description = "Operaciones relacionadas con las categorías")
public class CategoryController {
    private CategoryService categoryService;

    @Operation(summary = "Crear una nueva categoría", description = "Crea una categoría con el nombre especificado")
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente")
    @PostMapping()
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryCreateDto categoryCreateDto){
        CategoryDto categoryDto = categoryService.createCategory(categoryCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías existentes")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente")
    @GetMapping()
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría específica por su ID")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@Parameter(description = "ID de la categoría a eliminar") @PathVariable("id") Long id_category){
        categoryService.deleteCategory(id_category);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
