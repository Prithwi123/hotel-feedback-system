package com.cg.user.services;

import com.cg.user.entities.User;
import com.cg.user.entities.ConnectionUtility;
import com.cg.user.config.JaxbConfiguration;
import jakarta.json.JsonObject;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class JaxbProcessServiceImpl implements JaxbProcessService {

    @Autowired
    JaxbConfiguration jaxbConfiguration;

    private Logger logger = LoggerFactory.getLogger(JaxbProcessServiceImpl.class);

    public void pojoToJson(ConnectionUtility util, User user) throws JAXBException {

        logger.info("JAXB context configuration initialized");
        jaxbConfiguration.setJaxbContextUtility(util,user);

        logger.info("Marshaller configuration initialized");
        jaxbConfiguration.setMarshallUtility(util);

        //format it to json
        util.getMarshallerInput().setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        logger.info("setting marshalling format as JSON format");
        jaxbConfiguration.setStringWriterConfig(util);

        util.getMarshallerInput().marshal(user,util.getStringWriterInput());
        System.out.println(util.getStringWriterInput());
        logger.info("marshalling process done");

    }

    public void pojoToJsonFileCreate(ConnectionUtility util, User user) throws JAXBException {

        //creating the file name
        String uid = UUID.randomUUID().toString();
        LocalDate timer = LocalDate.now();
        String name = "data-"+timer.toString()+"-"+uid+".json";
        String path = "/Users/prithsar/Downloads/application/json/";
        String filename = path+name;
        jaxbConfiguration.setFileUtility(util, filename);
        logger.info("filename is created and initialized in jaxb context");

        jaxbConfiguration.setJaxbContextUtility(util,user);
        jaxbConfiguration.setMarshallUtility(util);
        logger.info("Marshaller configuration initialized");

        util.getMarshallerInput().setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        logger.info("setting marshalling format as JSON format");

        util.getMarshallerInput().marshal(user,util.getFileInput());
        logger.info("marshalling process done");
    }

    public void JsonToPojoConversion(ConnectionUtility util, String fileNameWithPath, User user) throws JAXBException{
        jaxbConfiguration.setJaxbContextUtility(util,user);
        logger.info("JAXB context configuration initialized");

        jaxbConfiguration.setUnMarshallerUtility(util);
        logger.info("Unmarshaller configuration initialized");

        util.getUnmarshallerInput().setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        util.getUnmarshallerInput().setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        logger.info("set content type as json");

        jaxbConfiguration.setFileUtility(util,fileNameWithPath);
        logger.info("setting the source location for json file");

        user =(User) util.getUnmarshallerInput().unmarshal(util.getFileInput());
        logger.info("POJO conversion done");
        System.out.println(user.toString());
    }

    public User JsonStringToPojo(JsonObject jsonObject, ConnectionUtility util, User user) throws JAXBException {
        jaxbConfiguration.setJaxbContextUtility(util,user);
        logger.info("JAXB context configuration initialized");

        jaxbConfiguration.setUnMarshallerUtility(util);
        logger.info("Unmarshaller configuration initialized");

        jaxbConfiguration.setStringWriterConfig(util);

        util.getUnmarshallerInput().setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        util.getUnmarshallerInput().setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        logger.info("set content type as json");

        User userResponse = (User) util.getUnmarshallerInput().unmarshal(new StringReader(jsonObject.toString()));
        logger.info("POJO conversion done");
        System.out.println(user.toString());
        return userResponse;
    }


}