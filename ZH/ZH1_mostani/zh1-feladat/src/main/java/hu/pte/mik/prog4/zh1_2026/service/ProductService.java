package hu.pte.mik.prog4.zh1_2026.service;

import hu.pte.mik.prog4.zh1_2026.model.Product;
import hu.pte.mik.prog4.zh1_2026.repository.ProductRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductService.class.toString());

    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = ProductRepository.getInstance();
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Product save(String name, String price, String description) {
        return this.productRepository.create(name, price, description);
    }

    public Product delete(Long id) {
        return this.productRepository.delete(id);
    }

    public void convertToXml(Object object) {
        this.checkIfSerializable(object);

        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\"?>").append(System.lineSeparator());
        try {
            this.serialize(object, stringBuilder);
        } catch (IllegalAccessException e) {
            throw new hu.pte.mik.prog4.zh1_2026.exception.ZH12026XmlException("Hiba az XML generálás során: " + e.getMessage());
        }

        LOGGER.info(stringBuilder.toString());
    }

    private void checkIfSerializable(Object object) {
        if (object == null) {
            throw new hu.pte.mik.prog4.zh1_2026.exception.ZH12026XmlException("Az object null.");
        }
        if (!object.getClass().isAnnotationPresent(hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Serializable.class)) {
            throw new hu.pte.mik.prog4.zh1_2026.exception.ZH12026XmlException("A class nincs annotálva a ZH12026Serializable annotációval.");
        }
    }

    private void serialize(Object object, StringBuilder stringBuilder) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Serializable classAnnotation = clazz.getAnnotation(hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Serializable.class);
        String classKey = classAnnotation.name().isEmpty() ? clazz.getSimpleName() : classAnnotation.name();

        this.appendStartTag(stringBuilder, classKey);
        stringBuilder.append(System.lineSeparator());

        for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Element.class)) {
                boolean originalAccessible = field.canAccess(object);
                field.setAccessible(true);

                hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Element fieldAnnotation = field.getAnnotation(hu.pte.mik.prog4.zh1_2026.annotation.ZH12026Element.class);
                String key = fieldAnnotation.name().isEmpty() ? field.getName() : fieldAnnotation.name();
                Object value = field.get(object);

                this.appendStartTag(stringBuilder, key);
                stringBuilder.append(value);
                this.appendEndTag(stringBuilder, key);

                field.setAccessible(originalAccessible);
            }
        }
        this.appendEndTag(stringBuilder, classKey);
    }

    private void appendStartTag(StringBuilder stringBuilder, String key) {
        stringBuilder.append("<")
                .append(key)
                .append(">");
    }

    private void appendEndTag(StringBuilder stringBuilder, String key) {
        stringBuilder.append("</")
                .append(key)
                .append(">")
                .append(System.lineSeparator());
    }

}
