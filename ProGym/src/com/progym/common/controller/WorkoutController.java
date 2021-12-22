package com.progym.common.controller;

import com.progym.common.model.FileModel;
import com.progym.common.model.T_workoutMainType;
import com.progym.common.model.T_workoutSubType;
import com.progym.common.service.UserService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkoutController {

    private static final String IMAGE_DIRECTORY = "/D:/imgdata/";


    @Autowired
    UserService userService;

    @ModelAttribute("workoutMainTypeList")
    public List<T_workoutMainType> getWorkoutMainTypeList() {
        return userService.getWorkoutMainTypeList();
    }

    @ModelAttribute("workoutSubTypeList")
    public List<T_workoutSubType> getWorkoutSubTypeList() {
        return userService.getWorkoutSubTypeList();
    }


    @RequestMapping(value = "/viewWorkouts", method = RequestMethod.GET)
    public ModelAndView viewWorkouts(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String mid) {

        FileModel file = new FileModel();
        ModelAndView mav = new ModelAndView("viewWorkouts", "fileUpload", file);
        if (!mid.isEmpty()) {
            mav.addObject("sList", getWorkoutSubTypeList().stream().filter(e -> e.getMtid() == Integer.parseInt(mid)).collect(Collectors.toList()));
            mav.addObject("mList", getWorkoutMainTypeList().stream().filter(e -> e.getId() == Integer.parseInt(mid)).collect(Collectors.toList()));
        } else {
            mav.addObject("sList", getWorkoutSubTypeList());
            mav.addObject("mList", getWorkoutMainTypeList().stream().filter(e ->
                            (
                                    !e.getName().equalsIgnoreCase("Single Muscle - 1") &&
                                            !e.getName().equalsIgnoreCase("Single Muscle - 2") &&
                                            !e.getName().equalsIgnoreCase("Single Muscle - 3") &&
                                            !e.getName().equalsIgnoreCase("Double Muscle - 1") &&
                                            !e.getName().equalsIgnoreCase("Double Muscle - 2") &&
                                            !e.getName().equalsIgnoreCase("Double Muscle - 3") &&
                                            !e.getName().equalsIgnoreCase("Ladies Level - 3")

                            )
                    ).collect(Collectors.toList())
            );
        }

            return mav;
    }


    @RequestMapping(value = "/uploadSubWorkoutGifAction", method = RequestMethod.POST)
    public void uploadSubWorkoutGifAction(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam String imgServerPath, @RequestParam String subWorkoutId, @RequestParam String mid,
                                          @Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors() || file.getFile().isEmpty()) {
            response.sendRedirect("viewWorkouts?mid=");
        } else {
            MultipartFile multipartFile = file.getFile();
            String uploadPath = session.getServletContext().getRealPath("/img/");

            //Now do something with file...
            String imagePath = IMAGE_DIRECTORY + imgServerPath;
            FileCopyUtils.copy(file.getFile().getBytes(), new File(imagePath));

            if (file.getFile().getOriginalFilename().contains("jpg"))
                imgServerPath = imgServerPath.concat(".jpg");
            if (file.getFile().getOriginalFilename().contains("png"))
                imgServerPath = imgServerPath.concat(".png");
            if (file.getFile().getOriginalFilename().contains("gif"))
                imgServerPath = imgServerPath.concat(".gif");
            transferImageToFtp(imagePath, imgServerPath, "subWorkoutGif");
            String serverimagePath = "https://tavrostechinfo.com/PROGYM/subWorkoutGifs/" + imgServerPath;
            userService.updateTSubworkoutType(subWorkoutId, serverimagePath);

            response.sendRedirect("viewWorkouts?mid=" + mid);
        }
    }


    @RequestMapping(value = "/updateTSubWorkoutName", method = RequestMethod.POST)
    public void updateTSubWorkoutName(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam String name, @RequestParam String subWorkoutId,
                                      @RequestParam String mid, @RequestParam Integer sets, @RequestParam Integer reps) throws IOException {
        userService.updateTSubWorkoutName(subWorkoutId, name, sets, reps);
        response.sendRedirect("viewWorkouts?mid=" + mid);
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

    @RequestMapping(value = "/addMainWorkout", method = RequestMethod.GET)
    public ModelAndView addMainWorkoutForm(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("addMainWorkoutForm");
        return mav;
    }


    @RequestMapping(value = "/addMainWorkout", method = RequestMethod.POST)
    public void addMainWorkoutToDatabase(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String workoutName) throws IOException {
        userService.addMainWorkoutToDatabase(workoutName);
        response.sendRedirect("viewWorkouts?mid=");
    }

    @RequestMapping(value = "/addSubWorkout", method = RequestMethod.GET)
    public ModelAndView addSubWorkoutForm(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("addSubTypeWorkoutForm");
        mav.addObject("workoutMainTypeList", getWorkoutMainTypeList());
        return mav;
    }

    @RequestMapping(value = "/addSubWorkout", method = RequestMethod.POST)
    public void addSubWorkoutToDatabase(HttpSession session, HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam String mainWorkoutId, @RequestParam String subWorkoutName,
                                        @RequestParam Integer sets, @RequestParam Integer reps) throws IOException {
        userService.addSubWorkoutToDatabase(mainWorkoutId, subWorkoutName, sets, reps);
        response.sendRedirect("viewWorkouts?mid=");
    }

    @RequestMapping(value = "/deleteMainWorkoutType", method = RequestMethod.GET)
    public void deleteMainWorkoutType(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String mainTypeId) throws IOException {
        userService.deleteMainWorkoutType(Integer.parseInt(mainTypeId));
        response.sendRedirect("viewWorkouts?mid=");
    }

    @RequestMapping(value = "/deleteSubWorkoutType", method = RequestMethod.GET)
    public void deleteSubWorkoutType(HttpSession session, HttpServletRequest request,
                                     HttpServletResponse response, @RequestParam String subTypeId, @RequestParam String mid) throws IOException {
        userService.deleteSubWorkoutType(Integer.parseInt(subTypeId));
        response.sendRedirect("viewWorkouts?mid=" + mid);
    }

}
