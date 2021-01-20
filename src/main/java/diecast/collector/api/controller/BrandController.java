package diecast.collector.api.controller;

import diecast.collector.api.api.BrandApi;
import diecast.collector.api.domain.Brand;
import diecast.collector.api.dto.BrandSaveRequest;
import diecast.collector.api.service.BrandService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/brand")
public class BrandController implements BrandApi {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public HttpResponse<Brand> getById(Integer id) {
        var brand = brandService.getById(id);

        return brand.isPresent()
                ? HttpResponse
                .ok(brand.get())
                : HttpResponse
                .notFound();
    }

    @Override
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @Override
    public HttpResponse<Brand> create(@Body @Valid BrandSaveRequest request) {
        var brand = brandService.create(request);

        return HttpResponse
                .created(brand, location(brand.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<Brand> update(Integer id, @Body @Valid BrandSaveRequest request) {
        var brand = brandService.getById(id);

        return brand.isPresent()
                ? HttpResponse
                .ok(brandService.update(brand.get(), request))
                .headers(headers -> headers.location(location(id)))
                : HttpResponse
                .notFound();
    }

    @Override
    @Transactional
    public HttpResponse<Brand> delete(Integer id) {
        var brandOptional = brandService.getById(id);
        if (brandOptional.isEmpty()) {
            return HttpResponse.notFound();
        }
        var brand = brandOptional.get();
        brandService.delete(brand);
        return HttpResponse.ok(brand);
    }

    private URI location(Integer id) {
        return URI.create("/brand/" + id);
    }
}