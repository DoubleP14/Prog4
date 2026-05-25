package hu.pte.mik.prog4.zh1_2026.repository;

import hu.pte.mik.prog4.zh1_2026.model.Product;
import hu.pte.mik.prog4.zh1_2026.util.IdProvider;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductRepository {

    private static final ProductRepository INSTANCE = new ProductRepository();

    private final Map<Long, Product> storage;
    private final IdProvider idProvider = IdProvider.getInstance();

    private ProductRepository() {
        this.storage = Stream.of(
                new Product(this.idProvider.getNewId(), "Dell XPS 13", "499990", "13.3\" ultrabook, Intel i7, 16GB RAM"),
                new Product(this.idProvider.getNewId(), "MacBook Air M2", "549990", "Apple M2 chip, 8GB RAM, 256GB SSD"),
                new Product(this.idProvider.getNewId(), "Logitech MX Master 3S", "29990", "Ergonomikus vezeték nélküli egér"),
                new Product(this.idProvider.getNewId(), "Keychron K8", "39990", "Mechanikus billentyűzet, hot-swap kapcsolók"),
                new Product(this.idProvider.getNewId(), "Samsung 27\" Monitor", "89990", "IPS kijelző, 144Hz, QHD"),
                new Product(this.idProvider.getNewId(), "Intel Core i7-13700K", "129990", "13. gen asztali processzor"),
                new Product(this.idProvider.getNewId(), "NVIDIA RTX 4070", "249990", "12GB GDDR6X videokártya"),
                new Product(this.idProvider.getNewId(), "Samsung 980 PRO 1TB", "34990", "NVMe SSD PCIe 4.0"),
                new Product(this.idProvider.getNewId(), "Corsair Vengeance 32GB", "44990", "DDR5 RAM kit (2x16GB)"),
                new Product(this.idProvider.getNewId(), "TP-Link Archer AX55", "24990", "Wi-Fi 6 router, gigabites portok")
        ).collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    public static ProductRepository getInstance() {
        return INSTANCE;
    }

    public Product findById(Long id) {
        return this.storage.get(id);
    }

    public List<Product> findAll() {
        return List.copyOf(this.storage.values());
    }

    public Product create(String name, String price, String description) {
        var id = this.idProvider.getNewId();
        return this.storage.put(id, new Product(id, name, price, description));
    }

    public Product delete(Long id) {
        return this.storage.remove(id);
    }

}
