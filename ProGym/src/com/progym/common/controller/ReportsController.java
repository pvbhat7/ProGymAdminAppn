package com.progym.common.controller;

import com.progym.common.constants.ProjectConstants;
import com.progym.common.model.CollectionDashboardPVO;
import com.progym.common.model.CollectionPVO;
import com.progym.common.model.FilterCollectionObject;
import com.progym.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ReportsController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/allReports", method = RequestMethod.GET)
    public ModelAndView showAllReports(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("display-reports");
        return fillCollectionDashboardData(mav);
    }

    private ModelAndView fillCollectionDashboardData(ModelAndView mav) {
        CollectionDashboardPVO c = userService.getDashboardCollection();
        mav.addObject("male", c.getMale());
        mav.addObject("female", c.getFemale());
        mav.addObject("steam", c.getSteam());
        mav.addObject("total", c.getTotal());
        mav.addObject("currentMale", c.getCurrentMonthMaleCollection());
        mav.addObject("currentFemale", c.getCurrentMonthFemaleCollection());
        mav.addObject("currentSteam", c.getCurrentMonthSteamCollection());
        mav.addObject("currentTotal", c.getCurrentMonthTotalCollection());
        mav.addObject("lastMale", c.getLastMonthMaleCollection());
        mav.addObject("lastFemale", c.getLastMonthFemaleCollection());
        mav.addObject("lastSteam", c.getLastMonthSteamCollection());
        mav.addObject("lastTotal", c.getLastMonthTotalCollection());
        mav.addObject("currentMonthName", c.getCurrentMonth());
        mav.addObject("lasttMonthName", c.getLastMonth());
        return mav;
    }

    @RequestMapping(value = "/viewCollectionByGMY", method = RequestMethod.GET)
    public ModelAndView viewCollectionByGMY(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam String gender, @RequestParam String month, @RequestParam String year) throws IOException {
        ModelAndView mav = new ModelAndView("display-reports");
        List<CollectionPVO> list = userService.getCollectionBy(new FilterCollectionObject("GMY", gender, month, year, null));
        mav.addObject("collectionDataPVOList", list);
        Double total = 0.00;
        for (CollectionPVO p : list) {
            total = total + p.getFeesPaid();
        }
        mav.addObject("filtername", "1d");
        mav.addObject("totalCollection", total);
        return fillCollectionDashboardData(mav);
    }

    @RequestMapping(value = "/viewCollectionByGD", method = RequestMethod.GET)
    public ModelAndView viewCollectionByGD(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam String gender, @RequestParam String date) throws IOException {
        ModelAndView mav = new ModelAndView("display-reports");
        List<CollectionPVO> list = userService.getCollectionBy(new FilterCollectionObject("GD", gender, null, null, date));
        mav.addObject("collectionDataPVOList", list);
        Double total = 0.00;
        for (CollectionPVO p : list) {
            total = total + p.getFeesPaid();
        }
        mav.addObject("filtername", "2d");
        mav.addObject("totalCollection", total);
        return fillCollectionDashboardData(mav);
    }

    @RequestMapping(value = "/viewCollectionByG", method = RequestMethod.GET)
    public ModelAndView viewCollectionByG(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam String gender) throws IOException {
        ModelAndView mav = new ModelAndView("display-reports");
        List<CollectionPVO> list = userService.getCollectionBy(new FilterCollectionObject("G", gender, null, null, null));
        mav.addObject("collectionDataPVOList", list);
        Double total = 0.00;
        for (CollectionPVO p : list) {
            total = total + p.getFeesPaid();
        }
        mav.addObject("filtername", "3d");
        mav.addObject("totalCollection", total);
        return fillCollectionDashboardData(mav);
    }

    @RequestMapping(value = "/sendPendingInvoices", method = RequestMethod.GET)
    public ModelAndView sendPendingInvoices(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("emailInvoiceFlag", userService.isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG));
        userService.sendPendingInvoices();
        return mav;
    }
}
