package com.cg.user.entities;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.StringWriter;

@Getter
@Setter
@NoArgsConstructor
public class ConnectionUtility {
    private JAXBContext jaxbContextInput;
    private Marshaller marshallerInput;
    private Unmarshaller unmarshallerInput;
    private StringWriter stringWriterInput;
    private File fileInput;

}
