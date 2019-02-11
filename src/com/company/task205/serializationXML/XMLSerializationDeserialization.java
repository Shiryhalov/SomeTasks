package com.company.task205.serializationXML;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLSerializationDeserialization {
    public static void main(String[] args) {
        Group group = new Group(355, "Applied Mathematics");
        Student student = new Student("Dmitry", "Shiryhalov", group);
        try (XMLEncoder xmlEncoder =
                     new XMLEncoder(new FileOutputStream("src/com/company/task205/serializationXML/Student.xml"))) {
            xmlEncoder.writeObject(student);
            xmlEncoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (XMLDecoder xmlDecoder =
                     new XMLDecoder(new FileInputStream("src/com/company/task205/serializationXML/Student.xml"))) {
            Student student1 = (Student) xmlDecoder.readObject();
            System.out.println(student1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
