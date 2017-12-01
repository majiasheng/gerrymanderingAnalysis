package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FileUploadForm;
import model.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.file.FileUploadService;

/**
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class FileController {

    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView handleUpload(
            @ModelAttribute FileUploadForm multipartFiles, 
            BindingResult result) {

        ModelAndView mv = new ModelAndView("file-upload");
        
        if (result.hasErrors()) {
            mv.addObject(SessionConstant.MSG_ATTRIBUTE,"<p style=\"color:red\">Error occurred in file upload</p>" + result.toString());
            return mv;
        }

        // check file type (csv only)
        MultipartFile geoMultipartFile = multipartFiles.getGeoData();
        if (!fileUploadService.isCSV(geoMultipartFile)) {
            mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.WRONG_FILE_FORMAT_MSG);
            return mv;
        }
        MultipartFile demographicMultipartFile = multipartFiles.getDemographicData();
        if (!fileUploadService.isCSV(demographicMultipartFile)) {
            mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.WRONG_FILE_FORMAT_MSG);
            return mv;
        }
        MultipartFile electionMultipartFile = multipartFiles.getElectionData();
        if (!fileUploadService.isCSV(electionMultipartFile)) {
            mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.WRONG_FILE_FORMAT_MSG);
            return mv;
        }

        // convert MultipartFile to File
        try {
            Map<String, File> files = new HashMap<String, File>();
            
            File geoFile = fileUploadService.multipartFileToFile(geoMultipartFile);
            File demographicFile = fileUploadService.multipartFileToFile(demographicMultipartFile);
            File electionFile = fileUploadService.multipartFileToFile(electionMultipartFile);

            files.put(SessionConstant.GEO_DATA_ATTRIBUTE, geoFile);
            files.put(SessionConstant.DEMOGRAPHIC_DATA_ATTRIBUTE, demographicFile);
            files.put(SessionConstant.GEO_DATA_ATTRIBUTE, electionFile);

            if (fileUploadService.handleFileUpload(files)) {
                mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.FILE_UPLOAD_SUCCESS_MSG);
                return mv;
            }

        } catch (IllegalStateException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.FILE_UPLOAD_FAILURE_MSG);
        return mv;
    }
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public ModelAndView handleUpload(@RequestParam Map<String, MultipartFile> multipartFiles) {
//
//        ModelAndView mv = new ModelAndView("file-upload");
//
//        // check file type (csv only)
//        MultipartFile geoMultipartFile = multipartFiles.get(SessionConstant.GEO_DATA_ATTRIBUTE);
//        if (!fileUploadService.isCSV(geoMultipartFile)) {
//            mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.WRONG_FILE_FORMAT_MSG);
//            return mv;
//        }
//        MultipartFile demographicMultipartFile = multipartFiles.get(SessionConstant.DEMOGRAPHIC_DATA_ATTRIBUTE);
//        if (!fileUploadService.isCSV(demographicMultipartFile)) {
//            mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.WRONG_FILE_FORMAT_MSG);
//            return mv;
//        }
//        MultipartFile electionMultipartFile = multipartFiles.get(SessionConstant.GEO_DATA_ATTRIBUTE);
//        if (!fileUploadService.isCSV(electionMultipartFile)) {
//            mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.WRONG_FILE_FORMAT_MSG);
//            return mv;
//        }
//
//        // convert MultipartFile to File
//        try {
//            Map<String, File> files = new HashMap<String, File>();
//            
//            File geoFile = fileUploadService.multipartFileToFile(geoMultipartFile);
//            File demographicFile = fileUploadService.multipartFileToFile(demographicMultipartFile);
//            File electionFile = fileUploadService.multipartFileToFile(electionMultipartFile);
//
//            files.put(SessionConstant.GEO_DATA_ATTRIBUTE, geoFile);
//            files.put(SessionConstant.DEMOGRAPHIC_DATA_ATTRIBUTE, demographicFile);
//            files.put(SessionConstant.GEO_DATA_ATTRIBUTE, electionFile);
//
//            if (fileUploadService.handleFileUpload(files)) {
//                mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.FILE_UPLOAD_SUCCESS_MSG);
//                return mv;
//            }
//
//        } catch (IllegalStateException ex) {
//            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        mv.addObject(SessionConstant.MSG_ATTRIBUTE, SessionConstant.FILE_UPLOAD_FAILURE_MSG);
//        return mv;
//    }

    @RequestMapping(value = "/file-upload", method = RequestMethod.GET)
    public ModelAndView goToUpload() {
        return new ModelAndView("file-upload");
    }

}
