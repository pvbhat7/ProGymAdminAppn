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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class FileUploadController {

    private static final String IMAGE_DIRECTORY = "/D:/imgdata/";

    @Autowired
    UserService userService;

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel file = new FileModel();
        ModelAndView modelAndView = new ModelAndView("uploadPhotoForm", "fileUpload", file);
        modelAndView.addObject("imageObject", userService.getImageObjectByBrand("progym"));
        return modelAndView;
    }

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.POST)
    public void fileUpload(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                           @RequestParam String imgServerPath, @RequestParam String mobile, @Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        String tavrosPath = "http://tavrostechinfo.com/PROGYM/brand/";
        String newDbImageName = "";
        String imgCol = imgServerPath;
        if (result.hasErrors() || file.getFile().isEmpty() && (mobile.isEmpty())) {
            response.sendRedirect("fileUploadPage");
        } else {
            if (!file.getFile().isEmpty()) {
                MultipartFile multipartFile = file.getFile();
                String uploadPath = session.getServletContext().getRealPath("/img/");
                Random r = new Random();
                int low = 10;
                int high = 100;
                int num = r.nextInt(high - low) + low;


                //Now do something with file...
                if (file.getFile().getOriginalFilename().contains("jpg"))
                    imgServerPath = imgServerPath.concat(String.valueOf(num)).concat(".jpg");
                if (file.getFile().getOriginalFilename().concat(String.valueOf(num)).contains("png"))
                    imgServerPath = imgServerPath.concat(".png");

                String imagePath = IMAGE_DIRECTORY + imgServerPath;
                FileCopyUtils.copy(file.getFile().getBytes(), new File(imagePath));

                newDbImageName = imgServerPath;
                transferImageToFtp(imagePath, newDbImageName, "dashboardPicture");
            }
            if (!newDbImageName.isEmpty())
                userService.updateBrandImageToDB(mobile, imgCol, tavrosPath + newDbImageName + "?" + ((int) ((Math.random() * (100 - 1)) + 1)));
            else
                userService.updateBrandImageToDB(mobile, imgCol, "");
            response.sendRedirect("fileUploadPage");
        }
    }

    // java(controller)
    @RequestMapping(value = "/saveCanvasImage1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveCanvasImage(
            @RequestParam(value = "imageBase64", defaultValue = "") String imageBase64) {
        Map<String, Object> res = new HashMap<String, Object>();

        File imageFile = new File("/home/data/canvasImage.png");
        try {
            byte[] decodedBytes = DatatypeConverter.parseBase64Binary(imageBase64.replaceAll("data:image/.+;base64,", ""));
            BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
            ImageIO.write(bfi, "png", imageFile);
            bfi.flush();
            res.put("ret", 0);
        } catch (Exception e) {
            res.put("ret", -1);
            res.put("msg", "Cannot process due to the image processing error.");
            return res;
        }

        return res;
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
