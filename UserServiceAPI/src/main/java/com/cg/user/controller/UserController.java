package com.cg.user.controller;


import java.util.List;

import com.cg.user.dto.UserPostDTO;
import com.cg.user.entities.ConnectionUtility;
import com.cg.user.entities.RequestFilePath;
import com.cg.user.entities.User;
import com.cg.user.mapper.MapStructMapper;
import com.cg.user.services.JaxbProcessService;
import com.cg.user.services.UserService;
import com.cg.user.vo.ResponseVO;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JaxbProcessService processService;

    @Autowired
    private MapStructMapper mapStructMapper;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserPostDTO userPostDTO){
        logger.info("user creation process started");
        User user1 = this.userService.saveUser(mapStructMapper.userPostDtoToUser(userPostDTO));
        logger.info("user creation done! user id is: "+user1.getUserId());
        return ResponseVO.userResponseBuilder("New User Entry successful",true,HttpStatus.CREATED,user1);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name="USER-SERVICE", fallbackMethod = "fallBackService")
    public ResponseEntity<Object> getSingleUser(@PathVariable String id){
        logger.info("process initiated to fetch record using specific user id");
        User user = userService.getUserById(id);
        return ResponseVO.userResponseBuilder("Requested user details given",true,HttpStatus.OK, mapStructMapper.userToUserGetDto(user));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUser(){
        logger.info("process initiated to fetch all user records");
        List<User> users =  userService.getAllUser();
        return ResponseVO.userResponseBuilder("user details given",true,HttpStatus.OK,users);
    }

    @GetMapping("/json/{id}")
    public ResponseEntity<Object> consolePrintPojoToJson(@PathVariable String id) throws JAXBException {
        logger.info("process initiated to convert POJO object to JSON and print in console");
        User user = userService.getUserById(id);
        logger.info("specific user fetched");
        ConnectionUtility util = new ConnectionUtility();
        processService.pojoToJson(util,user);
        return ResponseVO.jaxbResponseBuilder("Json object printed to console",true,HttpStatus.OK);

    }

    @GetMapping("/jsonFileCreate/{id}")
    public ResponseEntity<Object> userFilePojoToJson(@PathVariable String id) throws JAXBException {
        logger.info("process initiated to convert POJO object to JSON and write it in a file");
        User user = userService.getUserById(id);
        logger.info("specific user fetched");
        ConnectionUtility util = new ConnectionUtility();
        processService.pojoToJsonFileCreate(util, user);
        logger.info("file saved in /Users/prithsar/Downloads/application/json/");
        return ResponseVO.jaxbResponseBuilder("Json object stored in the given console",true,HttpStatus.OK);
    }

    @GetMapping("/jsonToPojo")
    public ResponseEntity<Object> convertJsonFileToPojo(@RequestBody RequestFilePath requestFilePath) throws JAXBException {
        logger.info("process initiated to convert json file to POJO object and print in console");
        User user = new User();
        ConnectionUtility util = new ConnectionUtility();
        processService.JsonToPojoConversion(util,requestFilePath.getFilepath(),user);
        return ResponseVO.jaxbResponseBuilder("Process Successful",true,HttpStatus.OK);
    }


    public ResponseEntity<Object> fallBackService(String id, Exception ex){
        logger.info("fallback method called");
        User user = User.builder()
                .email("dummy@email.com")
                .about("Service is down so dummy user is shown. Try after some time !!")
                .name("dummy")
                .userId("123456")
                .build();
        logger.info("temporary dummy user created");
        return ResponseVO.userResponseBuilder("Fallback method called",true,HttpStatus.OK, mapStructMapper.userToUserGetDto(user));
    }


}
