package diecast.collector.api.dto.response;

import java.util.List;

public class ModelsDashboardResponse {
    private List<ModelQuantityByAutomakerResponse> modelsByAutomaker;
    private List<ModelQuantityByCollectionResponse> modelsByCollection;

    public ModelsDashboardResponse(List<ModelQuantityByAutomakerResponse> modelsByAutomaker,
                                   List<ModelQuantityByCollectionResponse> modelsByCollection) {
        this.modelsByAutomaker = modelsByAutomaker;
        this.modelsByCollection = modelsByCollection;
    }

    public List<ModelQuantityByAutomakerResponse> getModelsByAutomaker() {
        return modelsByAutomaker;
    }

    public List<ModelQuantityByCollectionResponse> getModelsByCollection() {
        return modelsByCollection;
    }
}
