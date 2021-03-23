package com.mvoland.cov19api.tomove.web.update;

import com.mvoland.cov19api.tomove.data.update.entity.UpdateRequest;
import com.mvoland.cov19api.tomove.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/update")
public class UpdateController {

    private final UpdateService updateService;

    @Autowired
    public UpdateController(
            UpdateService updateService
    ) {
        this.updateService = updateService;
    }

    @GetMapping("requests")
    public List<UpdateRequest> getAllRequests() {
        return updateService.getAllUpdateRequests();
    }

    @GetMapping("requestHospDataDataGouvFrUpdate")
    public UpdateRequest requestUpdate() {
        return updateService
                .safeNewHospDataDataGouvFrUpdateRequest()
                .orElseThrow(() -> new CannotUpdateException("hospdata-datagouvfr"));

    }

}
