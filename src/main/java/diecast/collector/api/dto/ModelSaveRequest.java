package diecast.collector.api.dto;

import diecast.collector.api.enums.ModelScale;

import javax.validation.constraints.NotEmpty;

public class ModelSaveRequest {
    @NotEmpty
    private String name;
    private Integer modelYear;
    private ModelScale scale;
    private String colorRgba;
    private Integer automakerId;
    private Integer collectionId;
    private Integer brandId;

    public ModelSaveRequest() {}

    public ModelSaveRequest(@NotEmpty String name,
                            Integer modelYear,
                            ModelScale scale,
                            String colorRgba,
                            Integer automakerId,
                            Integer collectionId,
                            Integer brandId) {
        this.name = name;
        this.modelYear = modelYear;
        this.scale = scale;
        this.colorRgba = colorRgba;
        this.automakerId = automakerId;
        this.collectionId = collectionId;
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public ModelScale getScale() {
        return scale;
    }

    public void setScale(ModelScale scale) {
        this.scale = scale;
    }

    public String getColorRgba() {
        return colorRgba;
    }

    public void setColorRgba(String colorRgba) {
        this.colorRgba = colorRgba;
    }

    public Integer getAutomakerId() {
        return automakerId;
    }

    public void setAutomakerId(Integer automakerId) {
        this.automakerId = automakerId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
