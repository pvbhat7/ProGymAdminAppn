package com.progym.common.controller;

import com.progym.common.model.FileModel;
import com.progym.common.service.UserService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class OnlineDataController {

    private static final String IMAGE_DIRECTORY = "/D:/imgdata/";


    @Autowired
    UserService userService;

    @RequestMapping(value = "/supplements", method = RequestMethod.GET)
    public ModelAndView viewSupplements() {
        FileModel file = new FileModel();
        ModelAndView mav = new ModelAndView("viewOnlineProducts", "fileUpload", file);
        mav.addObject("listOfProducts", userService.viewSupplements());
        mav.addObject("objTypename", "Supplements");
        return mav;
    }

    @RequestMapping(value = "/merchandise", method = RequestMethod.GET)
    public ModelAndView viewMerchandise() {
        FileModel file = new FileModel();
        ModelAndView mav = new ModelAndView("viewOnlineProductsMerchandise", "fileUpload", file);
        mav.addObject("listOfProducts", userService.viewMerchandise());
        mav.addObject("objTypename", "Merchandise");
        return mav;
    }

    @RequestMapping(value = "/updateProductToServer", method = RequestMethod.POST)
    @ResponseBody
    public void updateProductToServer(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam String category, @RequestParam String productId,
                                      @RequestParam String productPhoto,@RequestParam String productPhotoDesc, @RequestParam String productName,
                                      @RequestParam String oldPrice, @RequestParam String newPrice) throws IOException {
        String uri = "";
        if (category.equalsIgnoreCase("Supplements")) {
            userService.updateProductToServer("Supplements", productId, productName, oldPrice, newPrice, productPhoto,productPhotoDesc, "false");
            uri = "supplements";
        } else if (category.equalsIgnoreCase("Merchandise")) {
            uri = "merchandise";
            userService.updateProductToServer("merchandise", productId, productName, oldPrice, newPrice, productPhoto,productPhotoDesc, "false");
        }

        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/uploadProductPhotoToServer", method = RequestMethod.POST)
    public void uploadProductPhotoToServer(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam String productId, @RequestParam String category,
                                           @Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        String uri = "";
        String newProductImagePath = "https://tavrostechinfo.com/PROGYM/";
        if (category.equalsIgnoreCase("Supplements")) {
            uri = "supplements";
            newProductImagePath = newProductImagePath + "supplements/";
        } else if (category.equalsIgnoreCase("Merchandise")) {
            uri = "merchandise";
            newProductImagePath = newProductImagePath + "merchandise/";
        }

        if (result.hasErrors() || file.getFile().isEmpty()) {
            response.sendRedirect(uri);
        } else {
            MultipartFile multipartFile = file.getFile();
            String uploadPath = session.getServletContext().getRealPath("/img/");

            //Now do something with file...
            String imagePath = IMAGE_DIRECTORY + file.getFile().getOriginalFilename();
            FileCopyUtils.copy(file.getFile().getBytes(), new File(imagePath));

            transferImageToFtp(imagePath, file.getFile().getOriginalFilename(), category);
            newProductImagePath = newProductImagePath + file.getFile().getOriginalFilename();

            //userService.updateTSubworkoutType(subWorkoutId,serverimagePath);
            userService.updateProductPhotoToServer(category, productId, newProductImagePath);
            response.sendRedirect(uri);
        }
    }

    @RequestMapping(value = "/uploadProductPhotoDescToServer", method = RequestMethod.POST)
    public void uploadProductPhotoDescToServer(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam String productId, @RequestParam String category,
                                           @Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        String uri = "supplements";

        if (result.hasErrors() || file.getFile().isEmpty()) {
            response.sendRedirect(uri);
        } else {
            MultipartFile multipartFile = file.getFile();
            String uploadPath = session.getServletContext().getRealPath("/img/");

            //Now do something with file...
            String imagePath = IMAGE_DIRECTORY + file.getFile().getOriginalFilename();
            FileCopyUtils.copy(file.getFile().getBytes(), new File(imagePath));

            transferImageToFtp(imagePath, file.getFile().getOriginalFilename(), "supplements");
            String newProductImagePath = "https://tavrostechinfo.com/PROGYM/supplements/" + file.getFile().getOriginalFilename();

            //userService.updateTSubworkoutType(subWorkoutId,serverimagePath);
            userService.updateProductPhotoDescToServer(category, productId, newProductImagePath);
            response.sendRedirect(uri);
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView orders(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                               @RequestParam String filter) {
        ModelAndView mav = new ModelAndView("viewOrders");
        mav.addObject("listOfOrders", userService.getAllOrders(filter));
        mav.addObject("filter", filter);
        return mav;
    }

    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.GET)
    public void updateOrderStatus(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam String order_id, @RequestParam String status, @RequestParam String filter) throws IOException {
        userService.updateOrderStatus(order_id, status);
        response.sendRedirect("orders?filter=" + filter);
    }

    public void transferImageToFtp(String filePath, String remoteFileName, String uploadType) {
        String server = "151.106.116.44";
        int port = 21;
        String user = "u636480992";
        String pass = "##Ppp7771";

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            ftpClient.changeWorkingDirectory("PROGYM");
            if (uploadType.equalsIgnoreCase("dashboardPicture")) {
                ftpClient.changeWorkingDirectory("brand");
            } else if (uploadType.equalsIgnoreCase("clientProfilePicture")) {
                ftpClient.changeWorkingDirectory("profilePictures");
            } else if (uploadType.equalsIgnoreCase("subWorkoutGif")) {
                ftpClient.changeWorkingDirectory("subWorkoutGifs");
            } else if (uploadType.equalsIgnoreCase("supplements")) {
                ftpClient.changeWorkingDirectory("supplements");
            } else if (uploadType.equalsIgnoreCase("merchandise")) {
                ftpClient.changeWorkingDirectory("merchandise");
            }

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(filePath);

            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Started uploading file");

            boolean done = ftpClient.storeFile(remoteFileName, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
