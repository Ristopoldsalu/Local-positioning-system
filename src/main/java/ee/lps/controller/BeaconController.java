package ee.lps.controller;

import ee.lps.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that serves the beacon.jsp and offers ajax services to it.
 */
@Controller
public class BeaconController {
    @Autowired
    private BuildingService buildingService;

    @RequestMapping(value = "/beacon", method = RequestMethod.GET)
    public ModelAndView getBeaconByMac(@RequestParam(required = false) String mac) {
        ModelAndView view = new ModelAndView("beacon");
        view.addObject("building", buildingService.getBuildingByMac(mac));
        return view;
    }

    @RequestMapping(value = "/loader", method = RequestMethod.GET)
    public ModelAndView loader() {
        return new ModelAndView("loader");
    }
}