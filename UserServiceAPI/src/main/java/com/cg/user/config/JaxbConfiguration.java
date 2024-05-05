package com.cg.user.config;

import com.cg.user.entities.ConnectionUtility;
import com.cg.user.entities.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JaxbConfiguration {


    public void setFileUtility(ConnectionUtility util, String filename){
        File file = new File(filename);
        util.setFileInput(file);
    }



    public void setJaxbContextUtility(ConnectionUtility util, User user) throws JAXBException {
        Map<String, Object> properties = new HashMap<String, Object>(2);
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, Boolean.TRUE);

        JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{user.getClass(), ObjectFactory.class}, properties);
        util.setJaxbContextInput(jaxbContext);
    }


    public void setMarshallUtility(ConnectionUtility util){
        Marshaller jaxbMarshaller = null;
        try {
            jaxbMarshaller = util.getJaxbContextInput().createMarshaller();
            util.setMarshallerInput(jaxbMarshaller);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void setUnMarshallerUtility(ConnectionUtility util){
        Unmarshaller jaxbUnmarshaller = null;
        try{
            jaxbUnmarshaller = util.getJaxbContextInput().createUnmarshaller();
            util.setUnmarshallerInput(jaxbUnmarshaller);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void setStringWriterConfig(ConnectionUtility util){
        StringWriter sw = new StringWriter();
        util.setStringWriterInput(sw);
    }
}
