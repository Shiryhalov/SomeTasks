package com.company.task205;

import java.io.*;
import javax.xml.bind.*;

import com.company.task205.city.*;

public class CityWithJAXB {
    public static void main(String[] args) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.company.task205.city");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CityData data = (CityData) unmarshaller.unmarshal(new FileInputStream("src/com/company/task205/City.xml"));
            for (CityData.DistrictsData.DistrictData c : data.getDistrictsData().getDistrictData()) {
                if (c.getName().equals("Московский")) {
                    c.getFirstNameData().setName("Сталинский");
                    break;
                }
            }
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(data, new FileWriter("CorrectedCity.xml"));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

}
