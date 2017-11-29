package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author majiasheng
 */
@Controller
public class FileController {
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public ModelAndView handleUpload() {
        //TODO: check file type (csv only)
        throw new UnsupportedOperationException("File upload not supported yet");
    }
    @RequestMapping(value="/file-upload", method=RequestMethod.GET)
    public ModelAndView goToUpload() {
        
        return new ModelAndView("file-upload");
    }
    
    
}
