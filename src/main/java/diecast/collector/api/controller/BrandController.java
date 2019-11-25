package diecast.collector.api.controller;

import diecast.collector.api.api.BrandApi;
import diecast.collector.api.domain.Brand;
import diecast.collector.api.dto.BrandSaveRequest;
import diecast.collector.api.service.BrandService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Controller("/brand")
public class BrandController implements BrandApi {
    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public HttpResponse<Brand> getById(Integer id) {
        var brand = brandService.getById(id).orElse(null);

        return Objects.nonNull(brand)
                ? HttpResponse
                .ok(brand)
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
        var brand = brandService.getById(id).orElse(null);

        return Objects.nonNull(brand)
                ? HttpResponse
                .ok(brandService.update(brand, request))
                .headers(headers -> headers.location(location(brand.getId())))
                : HttpResponse
                .notFound();
    }

    @Override
    @Transactional
    public HttpResponse<Brand> delete(Integer id) {
        var brand = brandService.getById(id).orElse(null);
        if (brand == null) {
            return HttpResponse.notFound();
        }
        brandService.delete(brand);
        return HttpResponse.ok(brand);
    }

    private URI location(Integer id) {
        return URI.create("/brand/" + id);
    }
}