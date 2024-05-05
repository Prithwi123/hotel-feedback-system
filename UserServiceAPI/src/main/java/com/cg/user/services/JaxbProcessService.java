package com.cg.user.services;

import com.cg.user.entities.ConnectionUtility;
import com.cg.user.entities.User;
import jakarta.xml.bind.JAXBException;

public interface JaxbProcessService {
    void pojoToJson(ConnectionUtility util, User user) throws JAXBException;
    void pojoToJsonFileCreate(ConnectionUtility util, User user) throws JAXBException;
    void JsonToPojoConversion(ConnectionUtility util, String fileNameWithPath, User user) throws JAXBException;
}
